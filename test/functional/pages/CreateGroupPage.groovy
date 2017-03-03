package pages

import geb.Page
import rpa.Researcher
import steps.InternationalizationHelper

class CreateGroupPage extends Page{
    InternationalizationHelper message = InternationalizationHelper.instance

    static url = "/RPA/researchGroup/create"

    static at = {
        title ==~ message.getMessage('default.create.label', 'ResearchGroup')
    }

    boolean createNewGroup(nome, pesq) {
        $("form").name = nome
        $("form").researchers = pesq.id
        $("input", name: "create").click()
    }

    boolean viewResearcher(pesq, num) {
        Researcher.findByName(pesq).id == num
    }
}
