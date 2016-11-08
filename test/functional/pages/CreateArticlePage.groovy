package pages

import geb.Page

class CreateArticlePage extends Page {
    static url = "/RPA/article/create/"

    static at = {
        title ==~ /Create Article/
    }

    boolean CreateArticle(String journalName){
        $("form").tittle = "Test Article"
        $("form").journal = journalName
        $("form").issn = "Test ISSN"
        $("input", name:"create").click()
    }
}
