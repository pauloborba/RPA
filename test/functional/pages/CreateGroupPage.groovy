package pages

import geb.Page
import rpa.ResearchGroup
import rpa.Researcher

class CreateGroupPage extends Page{
    static url = "/RPA/researchGroup/create"

    static at = {
        title ==~ /Create ResearchGroup/
    }

    boolean CreateNewGroup(nome, pesq) {
        $("form").name = nome
        $("form").researchers = pesq.id
        $("input", name: "create").click()
    }

    boolean ViewResearcher(pesq, num) {
        Researcher.findByName(pesq).id == num
    }
}
