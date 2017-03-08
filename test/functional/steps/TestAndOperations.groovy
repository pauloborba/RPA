package steps

import rpa.Article
import rpa.ResearcherController
import rpa.UpdateType
import rpa.XmlExtractorService
import rpa.Researcher
import rpa.UpdateLattes
import org.openqa.selenium.WebElement
import org.codehaus.groovy.grails.plugins.testing.GrailsMockMultipartFile

class TestAndOperations {
    private static final dirFiles = "test/functional/steps/"

    static public Researcher buildResearcherWithFile(String filename){
        def path = new File(dirFiles+filename)
        InputStream file = new FileInputStream(path);
        XmlExtractorService extractorService = new XmlExtractorService()
        Researcher researcher = extractorService.getResearcher(file)
        researcher
    }

    static public void importFile(String filename){
        def cont = new ResearcherController()
        def xmlInputStream = new FileInputStream(new File(dirFiles+filename));
        def multipartFile = new GrailsMockMultipartFile('file', xmlInputStream)
        cont.request.addFile(multipartFile)
        cont.importFile()
        cont.response.reset()
        cont.request.multipartFiles.clear()

    }

    static public boolean compareResearcherWithCpfAndName(Researcher r, String cpf, String name){
        return r != null && r.name == name && r.cpf == cpf
    }

    static public boolean compareArticle(Article a, String title, String journal, String issn){
        return a.title == title && a.journal == journal && a.issn == issn
    }

    static public boolean compareUpdateLattes(UpdateLattes d, String attribute, UpdateType typeUpdate){
        return d.attribute == attribute && d.typeUpdate == typeUpdate
    }

    static public boolean searchArticleByTitle(Researcher r, String a) {
        if (r == null || r.articles.size() == 0) return false
        else {
            for (int i = 0; i < r.articles.size(); i++) {
                if (r.articles[i].title == a) return true
            }
            return false
        }
    }

    static public boolean noResearchers() {
        return Researcher.findAll().empty
    }

    static public boolean archiveIsEmpty(filename) {
        def path = new File(dirFiles+filename)
        FileReader file = new FileReader(path)
        return (new BufferedReader(file).readLine() == null)
    }

    static public boolean containsText(String t, Collection<WebElement> allT){
        boolean contain = false
        for(a in allT){
            if(a.getText() == t){
                contain = true;
                break;
            }
        }
        contain
    }
}
