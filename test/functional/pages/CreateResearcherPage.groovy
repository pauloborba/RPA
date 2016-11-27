package pages

import geb.Page

class CreateResearcherPage extends Page{
    static url = "/RPA/researcher/create"

    static at = {
        title ==~ /Create Researcher/
    }

    boolean CreateNewResearcher(pesq, num) {
        $("form").name = pesq
        $("form").cpf = num
        $("input", name: "create").click()
    }
}
