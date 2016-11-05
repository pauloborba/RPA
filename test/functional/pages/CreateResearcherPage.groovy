package pages

import geb.Page
import rpa.Article

class CreateResearcherPage extends Page{
    static url ="/researcher/create/"

    static at = {
        title ==~ /Criar Researcher/
    }

    boolean CreateResearcher(String cpf, Set<Article> articles){
        $("form").name = "Higor Botelho"
        $("form").cpf = cpf
        $("form").articles = articles
        $("input", name:"create").click()
    }
}
