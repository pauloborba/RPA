package pages

import geb.Page

class CreateQualisAvaliationPage extends Page{
    static url ="/RPA/qualisAvaliation/create/"

    static at = {
        title ==~ /Create QualisAvaliation/
    }

    boolean CreateAvaliation(String journalName, String av){
        $("form").journal = journalName
        $("form").avaliation = av
        $("input", name:"create").click()
    }
}
