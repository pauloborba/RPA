package pages

import geb.Page

class ShowResearcherScorePage extends Page {
    static url = "/RPA/researcherScore/show/3/"

    static at = {
        title ==~ /Ver ResearcherScore/
    }

    boolean Showing(){
        $("a", text:"Not Avaliated: 1;")
    }

    boolean Listing(String res){
        $("a", text:res)
    }
}
