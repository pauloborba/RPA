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
        $('input[name="researcher"]').value(name)
    }

    void select() {
        $('input[type="submit"]').click()
    }

    def getNameValue() {
        return $('input[name="researcher"]').value()
    }

    def getCitationValue() {
        return $('input[name="citations"]').value()
    }
}
