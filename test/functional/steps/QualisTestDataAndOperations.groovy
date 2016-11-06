package steps

import org.springframework.mock.web.MockMultipartFile
import rpa.QualisController

class QualisTestDataAndOperations {

    def static createQualis(String title, String filename) {
        def controller = new QualisController()
        controller.params << [title: title]

        if(filename != null) {
            def path = System.getProperty('user.dir') + '/test/files/' + filename
            def inputStream = new FileInputStream(new File(path))
            def mockMultipart = new MockMultipartFile('qualis-sheet', filename, 'application/vnd.ms-excel', inputStream)
            controller.request.addFile(mockMultipart)
        }

        controller.save()
        controller.response.reset()
    }

}
