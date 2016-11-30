package steps

import rpa.ResearcherScore
import rpa.ResearcherScoreController
import rpa.Researcher
import rpa.Qualis
import rpa.QualisEvaluation
import rpa.Article
import pages.CreateArticlePage
import pages.CreateResearcherPage
import pages.CreateResearcherScorePage
import pages.CreateQualisEvaluationPage
import pages.CreateQualisPage
import pages.ShowResearcherScorePage
import pages.ListResearcherScorePage
import rpa.QualisEvaluationController
import rpa.QualisController
import rpa.ArticleController
import rpa.ResearcherController

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ResearcherScore score

def CreateQualisEvaluation(controller, journal, evaluation){
    def qe = new QualisEvaluation([journal: journal, avaliation: evaluation])
    controller.save(qe)
    controller.response.reset()
    qe
}

def CreateQualis(controller, year, evaluations){
    def qualis = new Qualis([description: year, avaliations: evaluations])
    controller.save(qualis)
    controller.response.reset()
    qualis
}

def CreateArticle(controller, name, journal, issn){
    def article = new Article([tittle: name , journal: journal, issn: issn])
    controller.save(article)
    controller.response.reset()
    article
}

def CreateResearcher(controller, name, cpf, articles){
    def researcher = new Researcher([name: name, cpf: cpf, articles: articles])
    controller.save(researcher)
    controller.response.reset()
    researcher
}

Given(~/^the qualis "([^"]*)" has avaliations for the journals entitled "([^"]*)", "([^"]*)" and "([^"]*)"$/) { String year, String journal1, String journal2, String journal3 ->
    def evaluations = new QualisEvaluation[3]
    def qeController = new QualisEvaluationController()
    evaluations[0] = CreateQualisEvaluation(qeController, journal1, "A1")
    evaluations[1] = CreateQualisEvaluation(qeController, journal2, "B2")
    evaluations[2] = CreateQualisEvaluation(qeController, journal3, "D1")
    def qController = new QualisController()
    CreateQualis(qController, year, evaluations)
}
And(~/^the researcher of cpf "([^"]*)" has only two articles: "([^"]*)" published at "([^"]*)" and "([^"]*)" published at "([^"]*)"$/) { String cpf, String article1, String journal1, String article2, String journal2 ->
    def articles = new Article[2]
    def aController = new ArticleController()
    articles[0] = CreateArticle(aController, article1, journal1, "TesteISSN1")
    articles[1] = CreateArticle(aController, article2, journal2, "TesteISSN2")
    def rController = new ResearcherController()
    CreateResearcher(rController, "Higor Botelho", cpf, articles)
}
When(~/^I ask for the score of cpf "([^"]*)" in the qualis "([^"]*)"$/) { String cpf, String year ->
    def researcher = Researcher.findByCpf(cpf)
    def qualis = Qualis.findByDescription(year)
    score = new ResearcherScore([researcher: researcher, qualis: qualis])
    def scoreController = new ResearcherScoreController()
    score.score = scoreController.CalculateScore(score)
    scoreController.save(score)
    scoreController.response.reset()
}
Then(~/^The system creates a string saying that "([^"]*)" article wasn't scored because its jornal wasn't found$/) { String number ->
    String cmp1 = "Not Avaliated: " + number + ";"
    assert score.score.contains(cmp1)
}

Given(~/^I created the reseacher of cpf "([^"]*)" with just an article published at "([^"]*)"$/) { String cpf, String journal ->
    to CreateArticlePage
    at CreateArticlePage
    page.CreateArticle(journal)
    to CreateResearcherPage
    at CreateResearcherPage
    Set<Article> h = new HashSet<Article>();
    h.add(Article.findByJournal(journal))
    page.CreateResearcher(cpf, h)
}
And(~/^I created the qualis "([^"]*)" with just an avaliation for "([^"]*)"$/) { String year, String journal ->
    to CreateQualisEvaluationPage
    at CreateQualisEvaluationPage
    page.CreateAvaliation(journal, "B2")
    to CreateQualisPage
    at CreateQualisPage
    Set<QualisEvaluation> h = new HashSet<QualisEvaluation>()
    h.add(QualisEvaluation.findByJournal(journal))
    page.CreateQualis(year, h)
}
When(~/^I ask to create the avaliation of the researcher of cpf "([^"]*)" in qualis "([^"]*)"$/) { String cpf, String year ->
    to CreateResearcherScorePage
    at CreateResearcherScorePage
    page.CreateScore(Researcher.findByCpf(cpf), Qualis.findByDescription(year))
}
Then(~/^I should see that "([^"]*)" article wasn't scored$/) { String arg1 ->
    at ShowResearcherScorePage
    page.ShowingNotAvaliatedArticle()
}
Given(~/^The qualis "([^"]*)" has no evaluations for the journal "([^"]*)"$/) { String year, String journal ->
    def evaluations = new QualisEvaluation[1]
    def qeController = new QualisEvaluationController()
    evaluations[0] = CreateQualisEvaluation(qeController, "Just Another Journal", "A1")
    def qController = new QualisController()
    def qualis = CreateQualis(qController, year, evaluations)
    boolean hasJournal = false;
    qualis.avaliations.each{ if(it.journal == journal) hasJournal = true}
    assert !hasJournal
}
And(~/^The researcher of cpf "([^"]*)" has only one article: "([^"]*)" published at "([^"]*)"$/) { String cpf, String article, String journal ->
    def articles = new Article[1]
    def aController = new ArticleController()
    articles[0] = CreateArticle(aController, article, journal, "TesteISSN")
    def rController = new ResearcherController()
    def researcher = CreateResearcher(rController, "Higor Botelho", cpf, articles)
    boolean hasAnotherArticle = false;
    researcher.articles.each {if(it.tittle != article) hasAnotherArticle = true}
    assert !hasAnotherArticle
}
When(~/^I ask the system for the score of the researcher of cpf "([^"]*)" in the qualis "([^"]*)"$/) { String cpf, String year ->
    def researcher = Researcher.findByCpf(cpf)
    def qualis = Qualis.findByDescription(year)
    score = new ResearcherScore([reseacher: researcher, qualis: qualis])
    def scoreController = new ResearcherScoreController()
    score.articlesNotFound = scoreController.NotAvaliated(researcher.articles, qualis.avaliations)
    scoreController.save(score)
    scoreController.response.reset()
}
Then(~/^The system also creates a list of articles not found containing only "([^"]*)"$/) { String article ->
    boolean hasArticle = false;
    boolean hasAnotherArticle = false;
    score.articlesNotFound.each{
        if(it.tittle == article) hasArticle = true
        else hasAnotherArticle = true
    }
    assert (hasArticle && !hasAnotherArticle)
}
And(~/^I created the avaliation of the researcher of cpf "([^"]*)" in qualis "([^"]*)"$/) { String cpf, String year ->
    to CreateResearcherScorePage
    at CreateResearcherScorePage
    page.CreateScore(Researcher.findByCpf(cpf), Qualis.findByDescription(year))
}
And(~/^I am at the list of avaliations page$/) { ->
    to ListResearcherScorePage
    at ListResearcherScorePage
}
When(~/^I ask to show the avaliation of researcher "([^"]*)"$/) { String cpf ->
    page.Click(Researcher.findByCpf(cpf).name)
}
Then(~/^I am at the description page for the avaliation$/) { ->
    at ShowResearcherScorePage
}
And(~/^I can see that "([^"]*)" journal wasn't scored$/) { String arg1 ->
    page.Listing(arg1)
}
