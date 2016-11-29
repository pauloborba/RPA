package steps

import rpa.Article
import rpa.ResearcherController
import rpa.XmlExtractorService
import rpa.Researcher

import org.codehaus.groovy.grails.plugins.testing.GrailsMockMultipartFile

class TestAndOperations {
    private static final dirFiles = "test/functional/steps/"

    static public Researcher buildResearcherWithFile(String filename){
        def path = new File(dirFiles+filename)
        InputStream file = new FileInputStream(path)

        XmlExtractorService extractorService = new XmlExtractorService()
        Researcher researcher = extractorService.getResearcher(file)
        researcher
    }

    static public Researcher buildResearcher(String name, String cpf){
        def researcher = new Researcher()
        researcher.name = name
        researcher.cpf = cpf
        researcher
    }

    static public Article buildArticle(String title, String journal, String issn){
        def article = new Article()
        article.tittle = title
        article.journal = journal
        article.issn = issn
        article
    }

    static public Researcher addArticleToResearcher(Researcher r, Article a){
        r.addToArticles(a)
        r
    }

    static public void createResearcher(Researcher r){
        def cont = new ResearcherController()
        cont.params << [name: r.name, cpf: r.cpf, articles: r.articles]
        cont.save()
        cont.response.reset()
    }

    static public void importFile(String filename){
        def cont = new ResearcherController()
        def xmlInputStream = new FileInputStream(new File(dirFiles+filename));
        def multipartFile = new GrailsMockMultipartFile('file', xmlInputStream)
        cont.request.addFile(multipartFile)
        cont.importFile()
        Researcher.all
        cont.response.reset()
    }

    static public boolean compareResearcherWithCpfAndName(Researcher r, String cpf, String name){
        return (r != null) && (r.name == name) && (r.cpf == cpf)
    }

    static public boolean compareArticle(Article a, String title, String journal, String issn){
        return a.tittle == title && a.journal == journal && a.issn == issn
    }

    static public boolean searchArticleByTitle(Researcher r, String a) {
        if (r == null || researcherHasNoArticles(r)) return false
        else {
            for (int i = 0; i < r.articles.size(); i++) {
                if (r.articles[i].tittle == a) return true
            }
            return false
        }
    }

    static public boolean researcherHasNoArticles(Researcher r) {
        return (r.articles == null || r.articles.size() < 1)
    }

    static public boolean noResearchers() {
        return Researcher.findAll().empty
    }

    static public boolean archiveIsEmpty(filename) {
        def path = new File(dirFiles+filename)
        FileReader file = new FileReader(path)
        return (new BufferedReader(file).readLine() == null)
    }
}