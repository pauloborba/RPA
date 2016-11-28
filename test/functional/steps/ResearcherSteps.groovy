package steps

import rpa.Researcher
import rpa.ResearcherController

/**
 * Created by rbb3 on 04/11/16.
 */
class ResearcherSteps {

    def static CreateResearcher(String name, String cpf) {
        def cont = new ResearcherController()
        cont.create()
        cont.save(new Researcher(name:name, cpf:cpf))
        cont.response.reset()
    }
}
