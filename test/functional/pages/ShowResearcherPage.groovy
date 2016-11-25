package pages

import geb.Page
import org.openqa.selenium.WebElement
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
    def findArticle(String title, String journal, String issn){
        def allTitles = $("span", class: "title").allElements()
        def allJournal = $("span", class: "journal").allElements()
        def allIssn = $("span", class: "issn").allElements()
        assert containsText(title, allTitles)
        assert containsText(journal, allJournal)
        assert containsText(issn, allIssn)

    }
    def compareReseacherWithArticle(String name, String cpf, String title, String journal, String issn){
        assert  $("span", id: "name-value").text() == name
        assert  $("span", id: "cpf-value").text() == cpf
        def allTitles = $("span", class: "title").allElements()
        def allJournal = $("span", class: "journal").allElements()
        def allIssn = $("span", class: "issn").allElements()
        assert containsText(title, allTitles)
        assert containsText(journal, allJournal)
        assert containsText(issn, allIssn)
    }

    def findAcceptedMsg(){
        def savedmsg = helperMsg.getMessage('researcher.saved')
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
}
