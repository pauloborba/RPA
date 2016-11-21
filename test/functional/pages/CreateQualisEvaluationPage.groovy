package pages

import geb.Page

class CreateQualisEvaluationPage extends Page{
    static url ="/RPA/qualisAvaliation/create/"

    static at = {
        title ==~ /Criar QualisEvaluation/
    }

    boolean CreateAvaliation(String journalName, String av){
        $("form").journal = journalName
        $("form").avaliation = av
        $("input", name:"create").click()
    }
}
