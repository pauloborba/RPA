package steps

import rpa.Article
import rpa.Researcher
import rpa.ResearcherController

/**
 * Created by rbb3 on 04/11/16.
 */
class ResearcherSteps {

    def static createResearcher(String name, String cpf) {
        def cont = new ResearcherController()
        cont.create()
        cont.save(new Researcher(name:name, cpf:cpf))
        cont.response.reset()
    }

    def static updateResearcher(Researcher researcher, Article article) {
        def cont = new ResearcherController()
        researcher.articles.add(article)
        cont.save(researcher)
        cont.response.reset()
    }
}
