package steps

import rpa.Article
import rpa.ResearcherController
import rpa.UpdateType
import rpa.XmlExtractorService
import rpa.Researcher
import rpa.UpdateLattes

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
        article.title = title
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
        cont.params << [newResearcher: r]
        cont.save()
        cont.response.reset()
    }

    static public void importFile(String filename){
        def cont = new ResearcherController()
        def xmlInputStream = new FileInputStream(new File(dirFiles+filename));
        def multipartFile = new GrailsMockMultipartFile('file', xmlInputStream)
        cont.request.addFile(multipartFile)
        cont.importFile()
        cont.response.reset()
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
}
