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
        InputStream file = new FileInputStream(path);
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
        cont.update()
        cont.response.reset()
    }

    static public boolean compareResearcherWithCpfAndName(Researcher r, String cpf, String name){
        return r != null && r.name == name && r.cpf == cpf
    }

    static public boolean compareArticle(Article a, String title, String journal, String issn){
        return a.tittle == title && a.journal == journal && a.issn == issn
    }

}
