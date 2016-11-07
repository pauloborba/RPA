package pages

import geb.Page
import rpa.QualisAvaliation

class CreateQualisPage extends Page{
    static url = "/RPA/qualis/create/"

    static at = {
        title ==~ /Criar Qualis/
    }

    boolean CreateQualis(String qualisYear, Set<QualisAvaliation> avs){
        $("form").year = qualisYear
        $("form").avaliations = avs
        $("input", name:"create").click()
    }
}
