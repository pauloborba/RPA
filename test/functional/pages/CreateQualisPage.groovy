package pages

import rpa.QualisEvaluation

class CreateQualisPage extends InternacionalizedPage{
    static url = "/RPA/qualis/create/"

    static at = {
        title ==~ helperMsg.getMessage('default.create.label', 'Qualis')
    }

    boolean CreateQualis(String qualisYear, Set<QualisEvaluation> avs){
        $("form").description = qualisYear
        $("form").avaliations = avs
        $("input", name:"create").click()
    }
}
