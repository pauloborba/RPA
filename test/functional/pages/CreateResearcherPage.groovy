package pages

import geb.Page
import steps.InternationalizationHelper

class CreateResearcherPage extends Page{
    InternationalizationHelper message = InternationalizationHelper.instance

    static url = "/RPA/researcher/create"

    static at = {
        title ==~ message.getMessage('default.create.label', 'Researcher')
    }

    boolean createNewResearcher(pesq, num) {
        $("form").name = pesq
        $("form").cpf = num
        $("input", name: "create").click()
    }
}
