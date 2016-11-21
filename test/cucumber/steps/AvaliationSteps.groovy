package cucumber.steps

import cucumber.api.PendingException
import pages.CreateQualisAvaliationPage
import pages.VisualizationAvaliationPage
import rpa.Article
import grails.test.GrailsUnitTestCase
import rpa.ArticleController
import rpa.Avaliation
import rpa.AvaliationController
import rpa.Qualis
import rpa.QualisAvaliationController
import rpa.QualisController
import rpa.Researcher
import rpa.QualisAvaliation
import rpa.ResearcherController
import pages.CreateAvaliationPage
import pages.CreateArticlePage
import pages.CreateQualisPage
import pages.CreateResearcherPage
import pages.CreateAvaliationPage


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def createArticle(title, journal, issn, controller){

    Article a = new Article([tittle: title, journal: journal, issn: issn])
    controller.save(a)
    controller.response.reset()

    a

}

def createQualisAvaliation(journal, avaliation, controller){

    QualisAvaliation qualisAvaliation = new QualisAvaliation([journal: journal, avaliation: avaliation])
    controller.save(qualisAvaliation)
    controller.response.reset()

    qualisAvaliation

}

def createQualis(year, avaliations, controller){

    Qualis qualis = new Qualis([year: year, avaliations: avaliations])
    controller.save(qualis)
    controller.response.reset()

    qualis

}

def createAvaliation(researcher, qualis, controller){

    Avaliation avaliation = new Avaliation([researcher: researcher, qualis:qualis])
    controller.save(avaliation)
    controller.response.reset()

    avaliation
}


//GUI
Given(~/^eu estou na pagina de avaliacao de notas e a pesquisadora "([^"]*)" e o qualis "([^"]*)" estao cadastrados$/) { String researcher, String qualis ->
    to CreateArticlePage
    page.CreateArticle("Journal")

    to CreateResearcherPage
    Set<Article> listArticles = new HashSet<Article>();
    listArticles.add(Article.findByJournal("Journal"))
    page.CreateResearcher(researcher, listArticles)

    to CreateQualisAvaliationPage
    page.CreateAvaliation("Journal", "B3")

    to CreateQualisPage
    QualisAvaliation qAvaliation1 = new QualisAvaliation([journal: "Journal", avaliation: "B3"])
    Set<QualisAvaliation> listQualisAvaliation = new HashSet<QualisAvaliation>()
    listQualisAvaliation.add(QualisAvaliation.findByAvaliation("B3"))
    page.CreateQualis(qualis, listQualisAvaliation)

}
When(~/^eu seleciono a pesquisadora cadastrada "([^"]*)" e o qualis "([^"]*)"$/) { String researcher, String qualis ->

    to CreateAvaliationPage
    at CreateAvaliationPage
    page.CreateScore(Researcher.findByName(researcher), Qualis.findByYear(qualis))

}
Then(~/^eh mostrado na tela uma lista com a quantidade de publicacoes que a pesquisadora "([^"]*)" tem por nota no qualis de "([^"]*)"$/) { String arg1, String arg2 ->

    at VisualizationAvaliationPage
    page.Visualization()

}

//CONTROLLER

Given(~/^o pesquisador cadastrado "([^"]*)" possui publicacoes "([^"]*)", "([^"]*)" e "([^"]*)" nos periodicos "([^"]*)", "([^"]*)" e "([^"]*)" respectivamente$/) { String researcher, String publication1, String publication2, String publication3, String journal1, String journal2, String journal3 ->
    def Articles = new Article[3]
    def ArticleController = new ArticleController()

    Articles[0] = createArticle(publication1, journal1, "10", ArticleController)
    Articles[1] = createArticle(publication2, journal2, "11", ArticleController)
    Articles[2] = createArticle(publication3, journal3, "12", ArticleController)

    Researcher r = new Researcher([name: researcher, cpf: "00011122233", articles: Articles])

    def ResearcherController = new ResearcherController()
    ResearcherController.save(r)
    ResearcherController.response.reset()

}
And(~/^o sistema contem o qualis "([^"]*)" com a nota "([^"]*)" para a publicacao "([^"]*)" e o qualis "([^"]*)" com a nota "([^"]*)" para as publicacoes "([^"]*)" e "([^"]*)"$/) { String qualis1, String score1, String publication1, String qualis2, String score2, String publication2, String publication3 ->
    def qualisAvaliation = new QualisAvaliation[1]
    def qualisAvaliation2 = new QualisAvaliation[2]
    def qualisAvaliationController = new QualisAvaliationController()

//    Set<QualisAvaliation> listQualisAvaliation = new HashSet<QualisAvaliation>()
//    listQualisAvaliation.add(qAvaliation1)
//
//    Set<QualisAvaliation> list2QualisAvaliation = new HashSet<QualisAvaliation>()
//    list2QualisAvaliation.add(qAvaliation2)
//    list2QualisAvaliation.add(qAvaliation3)

    qualisAvaliation[0] = createQualisAvaliation(publication1, score1, qualisAvaliationController)
    qualisAvaliation2[0] = createQualisAvaliation(publication2, score2, qualisAvaliationController)
    qualisAvaliation2[1] = createQualisAvaliation(publication3, score2, qualisAvaliationController)


    def qualisController = new QualisController()
    createQualis(qualis1, qualisAvaliation, qualisController)
    createQualis(qualis2, qualisAvaliation2, qualisController)

//    Qualis q1 = new Qualis([year: qualis1, avaliations: listQualisAvaliation])
//    Qualis q2 = new Qualis([year: qualis2, avaliations: list2QualisAvaliation])
//
//    q1.save flush: true
//    q2.save flush: true

}
When(~/^o sistema recebe a solicitacao de avaliar o pesquisador "([^"]*)" pelo qualis "([^"]*)" e "([^"]*)"$/) { String researcher, String qualis1, String qualis2 ->

    def AvaliationController = new AvaliationController()

    Avaliation av1 = createAvaliation(Researcher.findByName(researcher), Qualis.findByYear(qualis1), AvaliationController)
    Avaliation av2 = createAvaliation(Researcher.findByName(researcher), Qualis.findByYear(qualis2), AvaliationController)

    av1.categoryPoints = AvaliationController.CalculateScore(av1)
    av2.categoryPoints = AvaliationController.CalculateScore(av2)
    
}

Then(~/^o sistema retorna uma lista com a quantidade de publicacoes que o pesquisador "([^"]*)" tem por nota "([^"]*)" no qualis "([^"]*)"$/) { String researcher, String list, String qualis ->
    Avaliation av1 = Avaliation.findByResearcherAndQualis(Researcher.findByName(researcher), Qualis.findByYear(qualis))
    assert(av1.categoryPoints.equalsIgnoreCase(list))
}

And(~/^o sistema retorna outra lista com a quantidade de publicacoes que o pesquisador "([^"]*)" tem por nota "([^"]*)" no qualis "([^"]*)"$/) { String researcher, String list, String qualis ->
    Avaliation av2 = Avaliation.findByResearcherAndQualis(Researcher.findByName(researcher), Qualis.findByYear(qualis))
    assert(av2.categoryPoints.equalsIgnoreCase(list))
}
