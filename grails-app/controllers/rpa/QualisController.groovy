package rpa

import grails.transaction.Transactional
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row
import org.springframework.web.multipart.MultipartHttpServletRequest

class QualisController {

    static allowedMethods = [save: 'POST', delete: 'POST', update: 'POST']

    /**
     * Método para a obtenção dos qualis para a listagem
     * @return
     */
    @Transactional
    def index() {
        def qualises = Qualis.all
        [qualises: qualises, qualisesCount: qualises.size()]
    }

    /**
     * Método para a chamada da renderização da página de criar qualis
     * @return
     */
    @Transactional
    def create() {
        def qualisInstance = new Qualis()
        [qualis: qualisInstance]
    }

    /**
     * Método para a chamada da renderização da página de editar qualis
     * @return
     */
    @Transactional
    def edit() {
        def qualisInstance = Qualis.findById(params.id)
        if(qualisInstance == null) {
            flash.message = 'qualis.not.found.message'
            flash.default = 'Qualis não encontrado'
            redirect(controller: 'Qualis', action: 'index')
            return
        }
        [qualis: qualisInstance]
    }

    /**
     * Método que recebe requisição para atualizar título e/ou avaliações de um Qualis
     * @return
     */
    @Transactional
    def update() {
        if(params.id) {
            if(params.title) {
                def qualis = Qualis.findById(params.id)
                if(!qualis) {
                    flash.message = 'qualis.not.found.message'
                    flash.default = 'Qualis não encontrado'
                    redirect(controller: 'qualis', action: 'index')
                    return
                }
                qualis.setTitle(params.title)
                int cod = addQualisAvaliations(qualis)
                if(cod == 2) {
                    flash.message = 'qualis.invalid.file.message'
                    flash.message = 'Arquivo inválido'
                    qualis.delete()
                    redirect(controller: 'qualis', action: 'create')
                    return
                }
                redirect(controller: 'qualis', action: 'show', params: [id: params.id])
            } else {
                flash.message = 'default.blank.message'
                flash.args = [message(code: 'qualis.title.label'), 'qualis']
                flash.default = 'O campo título da classe qualis não pode ficar em branco'
                redirect(controller: 'qualis', action: 'edit', params: [id: params.id])
            }
        } else {
            flash.message = 'qualis.not.found.message'
            flash.default = 'Qualis não encontrado'
            redirect(controller: 'qualis', action: 'index')
        }
    }

    /**
     * Método que recebe requisição e deleta Qualis com seus QualisAvaliation
     * @return
     */
    @Transactional
    def delete() {
        def qualisInstance = Qualis.findById(params.id)
        if(qualisInstance == null) {
            flash.message = 'qualis.not.found.message'
            flash.default = 'Qualis não encontrado'
        } else {
            qualisInstance.delete(flush: true)
        }
        redirect(controller: 'qualis', action: 'index')
    }

    /**
     * Método que recebe requisição para renderizar view de detalhe para Qualis
     * @return
     */
    @Transactional
    def show() {
        def qualisInstance = Qualis.findById(params.id)
        if(qualisInstance == null) {
            flash.message = 'qualis.not.found.message'
            flash.default = 'Qualis não encontrado'
            redirect(controller: 'qualis', action: 'index')
            return
        }
        [qualis: qualisInstance]
    }

    /**
     * Método responsável por salvar o qualis oriundo da página create
     * @return
     */
    @Transactional
    def save() {
        if(params.title != null) {
            def tQualis = Qualis.findByTitle(params.title)
            if(tQualis != null) {
                tQualis.delete(flush: true)
            }
            def qualisInstance = new Qualis(title: params.title)
            qualisInstance.save()
            int cod = addQualisAvaliations(qualisInstance)
            if(cod == 1) {
                flash.message = 'default.blank.message'
                flash.args = [message(code: 'qualis.qualis-sheet.label'), 'qualis']
                flash.default = 'O campo planilha da classe qualis não pode ficar em branco'
                redirect(controller: 'qualis', action: 'create')
                qualisInstance.delete()
                return
            } else if(cod == 2) {
                flash.message = 'qualis.invalid.file.message'
                flash.message = 'Arquivo inválido'
                qualisInstance.delete()
                redirect(controller: 'qualis', action: 'create')
                return
            }
            redirect(controller: 'qualis', action: 'show', params: [id: qualisInstance.id])
        }
        else {
            flash.message = 'default.blank.message'
            flash.args = [message(code: 'qualis.title.label'), 'qualis']
            flash.default = 'O campo título da classe qualis não pode ficar em branco'
            redirect(controller: 'qualis', action: 'create')
        }
    }

    /**
     * Método para o processamento de uma planilha
     * Cada linha é processada para um QualisAvaliation e adicionado ao qualis em questão
     * @param qualis
     * @return
     */
    def int addQualisAvaliations(Qualis qualis) {
        if(request instanceof MultipartHttpServletRequest) {
            def file = saveUploadedFile(request, params.title)
            if(!file)
                return 1
            try {
                Workbook workbook = WorkbookFactory.create(file)
                Sheet sheet = workbook.getSheetAt(0)
                Iterator<Row> rows = sheet.iterator()
                rowsToQualisAvaliation(rows, qualis)
                workbook.close()
            } catch (Exception ex) {
                ex.printStackTrace()
                return 2
            }
            file.delete()
        } else
            return 1
        return 0
    }

    def static File saveUploadedFile(MultipartHttpServletRequest request, String fileName, String fileField = 'qualis-sheet') {
        def path = System.getProperty('user.dir') + '/grails-app/uploads/' + fileName + '.xls'
        def file = new File(path)
        def reqFile = request.getFile(fileField)
        if(reqFile) {
            reqFile.transferTo(file)
            return file
        } else
            return null
    }

    def static rowsToQualisAvaliation(Iterator<Row> rows, Qualis qualis) {
        DataFormatter dataFormatter = new DataFormatter()
        rows.next()
        while(rows.hasNext()) {
            def row = rows.next()
            def values = [
                    issn: dataFormatter.formatCellValue(row.getCell(0)),
                    journal: dataFormatter.formatCellValue(row.getCell(1)),
                    subject: dataFormatter.formatCellValue(row.getCell(2)),
                    evaluation: dataFormatter.formatCellValue(row.getCell(3))
            ]
            def qualisAvaliationInstance = new QualisAvaliation(values)
            qualis.addToQualisAvaliations(qualisAvaliationInstance)
        }
        qualis.save()
    }
}
