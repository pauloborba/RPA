package pages

import geb.Page

class CreateArticlePage extends InternacionalizedPage {
    static url = "/RPA/article/create/"

    static at = {
        title ==~ helperMsg.getMessage('default.create.label', 'Article')
    }

    boolean CreateArticle(String journalName){
        $("form").tittle = "Test Article"
        $("form").journal = journalName
        $("form").issn = "Test ISSN"
        $("input", name:"create").click()
    }
}
