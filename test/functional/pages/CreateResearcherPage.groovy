package pages

import geb.Page
import rpa.Article

class CreateResearcherPage extends InternacionalizedPage{
    static url ="/RPA/researcher/create/"

    static at = {
        title ==~ helperMsg.getMessage('default.create.label', 'Researcher')
    }


    boolean CreateResearcher(String name, Set<Article> articles){
        $("form").name = name
        $("form").cpf = "01010101011"
        $("form").articles = articles
        $("input", name:"create").click()
    }
}
