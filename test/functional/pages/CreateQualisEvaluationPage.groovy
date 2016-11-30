package pages

class CreateQualisEvaluationPage extends InternacionalizedPage{
    static url ="/RPA/qualisEvaluation/create/"

    static at = {
        title ==~ helperMsg.getMessage('default.create.label', 'QualisAvaliation')
    }

    boolean CreateAvaliation(String journalName, String av){
        $("form").journal = journalName
        $("form").avaliation = av
        $("input", name:"create").click()
    }
}
