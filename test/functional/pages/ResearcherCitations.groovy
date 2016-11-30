package pages

/**
 * Created by rbb3 on 30/11/16.
 */
class ResearcherCitations extends InternationalizedPage {
    static url = "/RPA/researcher/researcherCitations/"

    static at = {
        title ==~ helperMsg.getMessage('default.researchercitations.label', 'PesquidadorCitacoes')
    }

    void fillName(String name) {
        $("form").researcher = name
    }

    void select() {
        $("input", name:"buscar").click()
    }

    String getNameValue() {
        return $("form").researcher
    }

    int getCitationValue() {
        return $("form").citationsCount
    }
}
