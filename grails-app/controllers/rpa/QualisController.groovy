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
        // é obrigatória a presença de um id como parámetro da requisição
        if(params.id) {
            // campo título não pode ser vazio
            if(params.title) {
                def qualis = Qualis.findById(params.id)
                if(!qualis) // checamos pela existência de um qualis com tal id no sistema
                {
                    //caso não exista tal qualis, redirecionamos para a página de index
                    // com uma mensagem indicando o erro
                    flash.message = 'qualis.not.found.message'
                    flash.default = 'Qualis não encontrado'
                    redirect(controller: 'qualis', action: 'index')
                    return
                }
                qualis.setTitle(params.title) // substituimos o título pelo que vem na requisição
                if(request instanceof MultipartHttpServletRequest) // verificamos o tipo da requisição
                {
                    def file = request.getFile('qualis-sheet')
                    def path = System.getProperty('user.dir') + '/grails-app/uploads/' + ((String)params.title) + '.xls'
                    def fileSys = new File(path)
                    // verificamos se foi submetido um arquivo junto com o título
                    if(file) {
                        // se sim, copiamos o mesmo para a pasta de uploads
                        file.transferTo(fileSys)
                        qualis.qualisAvaliations.each {it.delete()}
                        qualis.qualisAvaliations.clear()
                        try {
                            // após excluir os QualisAvaliation's antigos, adicionamos os novos ao sistema
                            addQualisAvaliations(qualis, fileSys)
                        } catch (Exception ex) {
                            // em caso de erro com o arquivo, retornamos um mensagem de erro para o browser
                            flash.message = 'qualis.invalid.file.message'
                            flash.message = 'Arquivo inválido'
                            redirect(controller: 'qualis', action: 'edit', params: [id: params.id])
                            ex.printStackTrace()
                            return
                        }
                        //deletamos o arquivo ao fim do processamento
                        fileSys.delete()
                    }
                }
                redirect(controller: 'qualis', action: 'show', params: [id: params.id])
            } else {
                // caso não haja título, redirecionamos o mesmo
                flash.message = 'default.blank.message'
                flash.args = [message(code: 'qualis.title.label'), 'qualis']
                flash.default = 'O campo título da classe qualis não pode ficar em branco'
                redirect(controller: 'qualis', action: 'edit', params: [id: params.id])
            }
        } else {
            // caso não haja id, redirecionamos o mesmo
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
        // exigimos que o título tenha sido especificado
        if(params.title != null) {
            // verificamos se já existe um qualis com tal título
            def tQualis = Qualis.findByTitle(params.title)
            if(tQualis != null) {
                tQualis.delete(flush: true) // e o deletamos caso exista
            }

            def qualisInstance = new Qualis(title: params.title) // criamos uma nova instância com o título especificado
            qualisInstance.save()

            if(request instanceof MultipartHttpServletRequest) { // verificamos o tipo da requisição
                def path = System.getProperty('user.dir') + '/grails-app/uploads/' + ((String)params.title) + '.xls'
                def file = new File(path)
                def reqFile = request.getFile('qualis-sheet')

                if(!reqFile) // verificamos se o arquivo foi submetido
                {
                    flash.message = 'default.blank.message'
                    flash.args = [message(code: 'qualis.qualis-sheet.label'), 'qualis']
                    flash.default = 'O campo planilha da classe qualis não pode ficar em branco'
                    redirect(controller: 'qualis', action: 'create')
                    qualisInstance.delete()
                    return
                }
                reqFile.transferTo(file) // então transferimos ele para a pasta de upload

                try { // tentamos adicionar as avaliações ao qualis
                    addQualisAvaliations(qualisInstance, file)
                } catch (Exception ex) { // abortamos o processo no caso de um erro com o arquivo
                    flash.message = 'qualis.invalid.file.message'
                    flash.message = 'Arquivo inválido'
                    qualisInstance.delete()
                    redirect(controller: 'qualis', action: 'create')
                    ex.printStackTrace()
                    return
                }
                file.delete() // por fim, o arquivo é excluido
            } else { // de forma análoga, invalidamos a requisição na ausência de um arquivo
                flash.message = 'default.blank.message'
                flash.args = [message(code: 'qualis.qualis-sheet.label'), 'qualis']
                flash.default = 'O campo planilha da classe qualis não pode ficar em branco'
                redirect(controller: 'qualis', action: 'create')
                qualisInstance.delete()
                return
            }

            redirect(controller: 'qualis', action: 'show', params: [id: qualisInstance.id])
        }
        else { // é obrigatória a presença de um título
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
     * @param file
     * @return
     */
    def static addQualisAvaliations(Qualis qualis, File file) {
        // usamos a classe WorkbookFactory que se encarrega de avaliar o tipo de arquivo em questão
        Workbook workbook = WorkbookFactory.create(file)
        // o DataFormatter nos permite sempre extrair o conteúdo puro em formato de string
        DataFormatter dataFormatter = new DataFormatter()
        // selecionamos a primeira planilha
        Sheet sheet = workbook.getSheetAt(0)
        // criamos um iterator para as linha da planilha
        Iterator<Row> rows = sheet.iterator()
        rows.next() // damos um skip na primeira linha
        while(rows.hasNext()) {
            // para cada linha, convertemos a mesmo em um QualisAvaliation
            def row = rows.next()

            def values = [
                    issn: dataFormatter.formatCellValue(row.getCell(0)),
                    journal: dataFormatter.formatCellValue(row.getCell(1)),
                    subject: dataFormatter.formatCellValue(row.getCell(2)),
                    evaluation: dataFormatter.formatCellValue(row.getCell(3))
            ]

            def qualisAvaliationInstance = new QualisAvaliation(values)
            // então adicionamos a instância a relação one-to-many com Qualis
            qualis.addToQualisAvaliations(qualisAvaliationInstance)
        }
        qualis.save()
        // por fim, encerramos o arquivo
        workbook.close()
    }
}
