package steps

import org.springframework.http.HttpMethod
import org.springframework.mock.web.MockMultipartFile
import rpa.QualisController
import rpa.Qualis

class QualisTestDataAndOperations {

    /**
     * Método para simular uma requisição ao QualisController e realizar a adição de um Qualis
     * Também é simulada a submissão do própio arquivo
     * @param title
     * @param filename
     * @return
     */
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

    /**
     * Método para a simulação de uma requisição ao método delete() do QualisController
     * @param title
     * @return
     */
    def static deleteQualis(String title) {
        def qualis = Qualis.findByTitle(title)
        assert qualis != null
        def controller = new QualisController()
        controller.params << [id: qualis.id]

        controller.show()
        controller.delete()
        controller.response.reset()
    }

}
