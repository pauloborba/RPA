package pages

import geb.Page
import rpa.QualisEvaluation

class CreateQualisPage extends Page{
    static url = "/RPA/qualis/create/"

    static at = {
        title ==~ /Criar Qualis/
    }

    boolean CreateQualis(String qualisYear, Set<QualisEvaluation> avs){
        $("form").year = qualisYear
        $("form").avaliations = avs
        $("input", name:"create").click()
    }
}
