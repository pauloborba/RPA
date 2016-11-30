package pages

import rpa.Article

class CreateResearcherPage extends InternacionalizedPage{
    static url ="/RPA/researcher/create/"

    static at = {
        title ==~ helperMsg.getMessage('default.create.label', 'Researcher')
    }

    boolean CreateResearcher(String cpf, Set<Article> articles){
        $("form").name = "Higor Botelho"
        $("form").cpf = cpf
        $("form").articles = articles
        $("input", name:"create").click()
    }
}
