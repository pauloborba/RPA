package pages

import geb.Page

class ListResearcherScorePage extends Page{
    static url = "/RPA/researcherScore/index/"

    static at = {
        title ==~ /ResearcherScore Listagem/
    }

    boolean Click(String res){
        $("a", text:res).click()
    }

}
