package pages

import com.sun.org.apache.xml.internal.security.utils.I18n
import geb.Page
import org.openqa.selenium.WebElement

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
        boolean matchTitle = false;
        boolean matchJournal = false;
        boolean matchIssn = false;
    }

    def findAcceptedMsg(){
        assert $("div", class: "message").text() == "O pesquisador foi salvo com sucesso" ||
                $("div", class: "message").text() == "The researcher was successful saved"
    }

    def findDiff(String title, int type){
        def listDiff = $("span", class:"diff").allElements()
        for(diff in listDiff){
            if(type == 1){
                if(diff.getText() == "O artigo "+title+" foi adicionado" ||
                        diff.getText() == "The article "+title+" was added" ){
                    return true
                }
            }else if(type == 2){
                if(diff.getText() == "O artigo "+title+" foi removido" ||
                        diff.getText() == "The article "+title+" was removed" ){
                    return true
                }
            }else{
                if(diff.getText() == "O nome do pesquisador foi atualizado" ||
                        diff.getText() == "The name of reseacher was updated" ){
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
