package steps

import rpa.ResearcherScore
import rpa.ResearcherScoreController
import rpa.Researcher
import rpa.Qualis
import rpa.QualisAvaliation
import rpa.Article
import pages.CreateArticlePage
import pages.CreateResearcherPage
import pages.CreateResearcherScorePage
import pages.CreateQualisAvaliationPage
import pages.CreateQualisPage
import pages.ShowResearcherScorePage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ResearcherScore score

Given(~/^the qualis "([^"]*)" has avaliations for the journals entitled "([^"]*)", "([^"]*)" and "([^"]*)"$/) { String year, String journal1, String journal2, String journal3 ->
    QualisAvaliation av1 = new QualisAvaliation(journal1, "A1")
    QualisAvaliation av2 = new QualisAvaliation(journal2, "B2")
    QualisAvaliation av3 = new QualisAvaliation(journal3, "D1")
    Set<QualisAvaliation> avs = new HashSet<QualisAvaliation>()
    avs.add(av1)
    avs.add(av2)
    avs.add(av3)
    av1.save flush:true
    av2.save flush:true
    av3.save flush:true
    Qualis qualis = new Qualis(year, avs)
    qualis.save flush:true
}
And(~/^the researcher of cpf "([^"]*)" has only two articles: "([^"]*)" published at "([^"]*)" and "([^"]*)" published at "([^"]*)"$/) { String cpf, String article1, String journal1, String article2, String journal2 ->
    Article a1 = new Article(article1, journal1, "TesteISSN")
    Article a2 = new Article(article2, journal2, "TesteISSN")
    Set<Article> aSet = new HashSet<Article>();
    aSet.add(a1)
    aSet.add(a2)
    a1.save flush:true
    a2.save flush:true
    Researcher researcher = new Researcher("Higor Botelho", cpf, aSet)
    researcher.save flush:true
}
When(~/^I ask for the score of cpf "([^"]*)" in the qualis "([^"]*)"$/) { String cpf, String year ->
    Researcher researcher = Researcher.findByCpf(cpf)
    Qualis qualis = Qualis.findByYear(year)
    score = new ResearcherScore(researcher, qualis)
    ResearcherScoreController scoreController = new ResearcherScoreController()
    score.score = scoreController.CalculateScore(score)
    score.save flush:true
}
Then(~/^The system creates a string saying that "([^"]*)" article wasn't scored because it's jornal wasn't found$/) { String number ->
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
    to CreateQualisAvaliationPage
    at CreateQualisAvaliationPage
    page.CreateAvaliation(journal, "B2")
    to CreateQualisPage
    at CreateQualisPage
    Set<QualisAvaliation> h = new HashSet<QualisAvaliation>()
    h.add(QualisAvaliation.findByJournal(journal))
    page.CreateQualis(year, h)
}
When(~/^I ask to create the avaliation of the researcher of cpf "([^"]*)" in qualis "([^"]*)"$/) { String cpf, String year ->
    to CreateResearcherScorePage
    at CreateResearcherScorePage
    page.CreateScore(Researcher.findByCpf(cpf), Qualis.findByYear(year))
}
Then(~/^I should see that "([^"]*)" article wasn't scored$/) { String arg1 ->
    to ShowResearcherScorePage
    at ShowResearcherScorePage
    page.Showing()
}
Given(~/^The qualis "([^"]*)" has avaliations no avaliation for the journal "([^"]*)"$/) { String year, String journal ->
    QualisAvaliation av1 = new QualisAvaliation("Just Another Journal", "A1")
    Set<QualisAvaliation> avs = new HashSet<QualisAvaliation>()
    avs.add(av1)
    av1.save flush:true
    Qualis qualis = new Qualis(year, avs)
    qualis.save flush:true
    boolean hasJournal = false;
    qualis.avaliations.each{ if(it.journal == journal) hasJournal = true}
    assert !hasJournal
}
And(~/^The researcher of cpf "([^"]*)" has only one article: "([^"]*)" published at "([^"]*)"$/) { String cpf, String article, String journal ->
    Article a1 = new Article(article, journal, "TesteISSN")
    Set<Article> aSet = new HashSet<Article>();
    aSet.add(a1)
    a1.save flush:true
    Researcher researcher = new Researcher("Higor Botelho", cpf, aSet)
    researcher.save flush:true
    boolean hasAnotherArticle = false;
    researcher.articles.each {if(it.tittle != article) hasAnotherArticle = true}
    assert !hasAnotherArticle
}
When(~/^The system create a score for the reseacher of cpf "([^"]*)" in the qualis "([^"]*)"$/) { String cpf, String year ->
    Researcher researcher = Researcher.findByCpf(cpf)
    Qualis qualis = Qualis.findByYear(year)
    score = new ResearcherScore(researcher, qualis)
    ResearcherScoreController scoreController = new ResearcherScoreController()
    score.articlesNotFound = scoreController.NotAvaliated(researcher.articles, qualis.avaliations)
    score.save flush:true
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