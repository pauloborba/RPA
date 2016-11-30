package pages

import rpa.Researcher
import rpa.Qualis

class CreateResearcherScorePage extends InternacionalizedPage {
    static url = "/RPA/researcherScore/create/"

    static at ={
        title ==~ helperMsg.getMessage('default.create.label', 'ResearcherScore')
    }

    boolean CreateScore(Researcher r, Qualis l){
        $("form").researcher = r
        $("form").qualis = l
        $("input", name:"create").click()
    }
}
