package pages

import geb.Page
import rpa.Researcher
import rpa.Qualis

class CreateReseacherScorePage extends Page {
    static url = "/RPA/researcherScore/create/"

    static at ={
        title ==~ /Criar ReseacherScore/
    }

    boolean CreateScore(Researcher r, Qualis l){
        $("form").researcher = r
        $("form").qualis = l
        $("input", name:"create").click()
    }
}
