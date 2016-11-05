package pages

import geb.Page

class CreateArticlePage extends Page {
    static url ="/article/create/"

    static at = {
        title ==~ /Criar Article/
    }

    boolean CreateArticle(String journal){
        $("form").tittle = "Test Article"
        $("form").journal = journal
        $("form").issn = "Test ISSN"
        $("input", name:"create").click()
    }
}
