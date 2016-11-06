package rpa

import grails.validation.Validateable
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Row
import org.springframework.web.multipart.MultipartHttpServletRequest

class QualisController {

    static allowedMethods = [save: 'POST']

    def save() {
        if(params.title != null) {
            def tQualis = Qualis.findByTitle(params.title)
            if(tQualis != null) {
                tQualis.delete(flush: true)
            }

            def qualisInstance = new Qualis(title: params.title)

            qualisInstance.save(flush: true)

            if(request instanceof MultipartHttpServletRequest) {
                def path = System.getProperty('user.dir') + '/grails-app/uploads/' + ((String)params.title) + '.xls'
                def file = new File(path);

                ((MultipartHttpServletRequest)request)
                    .getFile('qualis-sheet')
                    .transferTo(file)

                addQualisAvaliations(qualisInstance, file)
                file.delete()
            }
        }
        else {
            
        }
    }

    def addQualisAvaliations(Qualis qualis, File file) {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file))
        HSSFSheet sheet = workbook.getSheetAt(0)
        Iterator<Row> rows = sheet.iterator()
        rows.next()
        while(rows.hasNext()) {
            def row = rows.next()

            def values = [
                    issn: row.getCell(0).stringCellValue,
                    journal: row.getCell(1).stringCellValue,
                    subject: row.getCell(2).stringCellValue,
                    evaluation: row.getCell(3).stringCellValue
            ]

            def qualisAvaliationInstance = new QualisAvaliation(values)
            qualis.addToQualisAvaliations(qualisAvaliationInstance)
            qualisAvaliationInstance.save(flush: true)
        }
    }
}
