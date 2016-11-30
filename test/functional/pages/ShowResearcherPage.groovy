package pages

import geb.Page
import org.openqa.selenium.WebElement
import steps.InternationalizationHelper

class ShowResearcherPage extends Page {
    static url = "RPA/researcher/show"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        title ==~ helper.getMessage('default.show.label', "Researcher")
    }

    boolean containsText(String t, Collection<WebElement> allT){
        boolean contain = false
        for(a in allT){
            if(a.getText() == t){
                contain = true;
                break;
            }
        }
        contain
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
