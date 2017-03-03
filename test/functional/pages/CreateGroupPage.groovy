package pages

import geb.Page
import rpa.Researcher
import steps.InternationalizationHelper

class CreateGroupPage extends PageWithI18nSupport{
    static url = "/RPA/researchGroup/create"

    static at = {
        def researchGroup = helperMsg.getMessage('researchGroup.label')
        title ==~ helperMsg.getMessage('default.create.label', researchGroup)
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
