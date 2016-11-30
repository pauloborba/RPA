package pages

import geb.Page
import rpa.QualisAvaliation

class CreateQualisPage extends InternacionalizedPage{
    static url = "/RPA/qualis/create/"

    static at = {
        title ==~ helperMsg.getMessage('default.create.label', 'Qualis')
    }

    boolean CreateQualis(String qualisYear, Set<QualisAvaliation> avs){
        $("form").year = qualisYear
        $("form").avaliations = avs
        $("input", name:"create").click()
    }
}
