package pages

import geb.Page
import org.openqa.selenium.WebElement
import rpa.UpdateType

class ShowReseacherPage extends PageWithI18nSupport {
    static url = "/RPA/researcher/show/"

    static at =  {
        //getMessage é um método da super classe
        def researcherlabel = getMessage('researcher.label')
        def titlemsg = getMessage('default.show.label', researcherlabel)
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
        def savedmsg = getMessage('researcher.saved')
        assert $("div", class: "message").text() == savedmsg
    }

    def findUpdateLattes(String title, UpdateType type){
        def listUpdateLattes = $("span", class:"updateLattes").allElements()
        for(updateLattes in listUpdateLattes){
            if(type == UpdateType.ADD_ARTICLE){
                if(updateLattes.getText() == getMessage('updateLattes.added',title)){
                    return true
                }
            }else if(type == UpdateType.REMOVE_ARTICLE){
                if(updateLattes.getText() == getMessage('updateLattes.removed',title)){
                    return true
                }
            }
        }
        return false
    }

    def findReseacherWithTwoArticlesSameJournal(String name, String cpf, String title1, String title2, String journal, String issn) {
        compareReseacherWithArticle(name,cpf,title1,journal,issn)
        compareReseacherWithArticle(name,cpf,title2,journal,issn)
    }
}
