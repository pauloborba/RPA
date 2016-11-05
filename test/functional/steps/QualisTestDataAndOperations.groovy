package steps

import org.springframework.mock.web.MockMultipartFile
import rpa.QualisController

class QualisTestDataAndOperations {

    def static createQualis(String title, String filename) {
        def controller = new QualisController()
        controller.params << [title: title]

        controller.request.addFile(new MockMultipartFile('qualis-sheet', filename, 'application/vnd.ms-excel', new FileInputStream(new File(System.getProperty('user.dir') + '/test/files/' + filename))))
        controller.save()
        controller.response.reset()
    }

}
