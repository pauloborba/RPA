package pages

import geb.Page
import org.openqa.selenium.WebElement
import rpa.UpdateLattes
import rpa.UpdateType

class ShowResearcherPage extends PageWithI18nSupport {
    static url = "/RPA/researcher/show/"

    static at =  {
        def researcherlabel = helperMsg.getMessage('researcher.label')
        def titlemsg = helperMsg.getMessage('default.show.label', researcherlabel)
        title == titlemsg
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

    def findResearcher(String cpf, String name){
        assert  $("span", id: "name-value").text() == name
        assert  $("span", id: "cpf-value").text() == cpf
    }

    def findArticleWithTwoAuthors(String title, String journal, String issn, String author1, String author2){
        def allTitles = $("span", class: "title").allElements()
        def allJournal = $("span", class: "journal").allElements()
        def allIssn = $("span", class: "issn").allElements()
        def allAuthors = $("span", class: "author").allElements()
        assert containsText(title, allTitles)
        assert containsText(journal, allJournal)
        assert containsText(issn, allIssn)
        assert containsText(author1, allAuthors)
        assert containsText(author2, allAuthors)

    }

    def findAcceptedMsg(boolean updated){
        def savedmsg = helperMsg.getMessage('researcher.saved')
        if (updated) {
            savedmsg = helperMsg.getMessage('researcher.updated')
        }
        assert $("div", class: "message").text() == savedmsg
    }

    def findUpdateLattes(String title, UpdateType type){
        def listUpdateLattes = $("span", class:"updateLattes").allElements()
        for(updateLattes in listUpdateLattes){
            if(type == UpdateType.ADD_ARTICLE){
                if(updateLattes.getText() == helperMsg.getMessage('updateLattes.added',title)){
                    return true
                }
            }else if(type == UpdateType.REMOVE_ARTICLE){
                if(updateLattes.getText() == helperMsg.getMessage('updateLattes.removed',title)){
                    return true
                }
            }
        }
        return false
    }

    def findAmountUpdates(int amount){
        def listUpdateLattes = $("span", class:"updateLattes").allElements()
        assert listUpdateLattes.size() == amount
    }

    def findAmountArticles(int amount){
        def allTitles = $("span", class: "title").allElements()
        assert allTitles.size() == amount
    }

    def findAmountAlertMsg(int amount){
        def allAlertMsg = $("span", class: "lastUpdate").allElements()
        assert allAlertMsg.size() == amount
    }

    def findAlertMsg(){
        def alertLastUpdates = $("ul", class:"message lastUpdates")
        assert alertLastUpdates != null
    }

    def findUpdateOnAlert(String title, UpdateType type){
        def allAlerts = $("span", class:"lastUpdate").allElements()
        if(type == UpdateType.ADD_ARTICLE){
            assert containsText(helperMsg.getMessage('updateLattes.added',title), allAlerts)
        }else{
            assert containsText(helperMsg.getMessage('updateLattes.removed',title), allAlerts)
        }

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
