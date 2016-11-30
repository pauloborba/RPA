package pages

import geb.Page

class CreateQualisAvaliationPage extends InternacionalizedPage{
    static url ="/RPA/qualisAvaliation/create/"

    static at = {
        title ==~ helperMsg.getMessage('default.create.label', 'QualisAvaliation')
    }


    boolean CreateAvaliation(String journalName, String av){
        $("form").journal = journalName
        $("form").avaliation = av
        $("input", name:"create").click()
    }
}
