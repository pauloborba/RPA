package pages

import geb.Page
import org.openqa.selenium.WebElement
import rpa.UpdateType

class ShowReseacherPage extends Page {
    static url = "/RPA/researcher/show/"

    static at =  {
        title ==~ /Ver Pesquisador/ || title ==~ /Show Researcher/
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
        assert $("div", class: "message").text() == "O pesquisador foi salvo com sucesso" ||
                $("div", class: "message").text() == "The researcher was successfully saved"
    }

    def findUpdateLattes(String title, UpdateType type){
        def listUpdateLattes = $("span", class:"updateLattes").allElements()
        for(updateLattes in listUpdateLattes){
            if(type == UpdateType.ADD_ARTICLE){
                if(updateLattes.getText() == "O artigo "+title+" foi adicionado" ||
                        updateLattes.getText() == "The article "+title+" was added" ){
                    return true
                }
            }else if(type == UpdateType.REMOVE_ARTICLE){
                if(updateLattes.getText() == "O artigo "+title+" foi removido" ||
                        updateLattes.getText() == "The article "+title+" was removed" ){
                    return true
                }
            }else{
                if(updateLattes.getText() == "O nome do pesquisador foi atualizado" ||
                        updateLattes.getText() == "The name of reseacher was updated" ){
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
