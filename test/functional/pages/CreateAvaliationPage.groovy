package pages

import geb.Page
import rpa.Researcher
import rpa.Qualis

class CreateAvaliationPage extends InternacionalizedPage{

    static url = "/RPA/avaliation/create"

    static at ={
        title ==~ helperMsg.getMessage('default.create.label', 'Avaliation')
    }


    boolean CreateScore(Researcher r, Qualis l){
        $("form").researcher = r
        $("form").qualis = l
        $("input", name:"create").click()
    }

}
