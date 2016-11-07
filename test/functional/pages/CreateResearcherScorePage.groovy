package pages

import geb.Page
import rpa.Researcher
import rpa.Qualis

class CreateResearcherScorePage extends Page {
    static url = "/RPA/researcherScore/create/"

    static at ={
        title ==~ /Criar ResearcherScore/
    }

    boolean CreateScore(Researcher r, Qualis l){
        $("form").researcher = r
        $("form").qualis = l
        $("input", name:"create").click()
    }
}
