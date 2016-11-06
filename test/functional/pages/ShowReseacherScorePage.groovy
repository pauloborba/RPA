package pages

import geb.Page

class ShowReseacherScorePage extends Page {
    static url = "/RPA/researcherScore/show/1/"

    static at = {
        title ==~ /Ver ResearcherScore/
    }

    boolean Showing(){
        $("a", text:"Not Avaliated: 1;")
    }
}
