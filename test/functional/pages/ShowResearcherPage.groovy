package pages

import geb.Page
import steps.InternationalizationHelper
import static steps.TestAndOperations.containsText

class ShowResearcherPage extends Page {
    static url = "RPA/researcher/show"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        title ==~ helper.getMessage('default.show.label', "Researcher")
    }

    def findAcceptedMsg(){
        InternationalizationHelper helper = InternationalizationHelper.instance
        assert $("div", class: "message").text() == helper.getMessage('researcher.saved')
    }

    boolean checkName(String nome){
        return $("span", id: "name-value").text() == nome
    }

    boolean checkCpf(String cpf){
        return $("span", id: "cpf-value").text() == cpf
    }

    boolean checkArticle(String title, String journal, String issn){
        boolean matchTitle = containsText(title, $("span", class: "title").allElements())
        boolean matchJournal = containsText(journal, $("span", class: "journal").allElements())
        boolean matchIssn = containsText(issn, $("span", class: "issn").allElements())
        return matchTitle & matchJournal & matchIssn
    }

    boolean areThereArticles() {
        return $("span", id: "articles-value").text() != null
    }
}
