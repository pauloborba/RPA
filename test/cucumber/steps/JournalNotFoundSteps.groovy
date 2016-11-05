package steps

import cucumber.api.PendingException

import pages.ResearcherScorePage
import rpa.ResearcherScore
import rpa.ResearcherScoreController
import rpa.Researcher
import rpa.ResearcherController
import rpa.Qualis
import rpa.QualisController
import rpa.QualisAvaliation
import rpa.QualisAvaliationController
import rpa.Article
import rpa.ArticleController

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
Given(~/^I am at the score page for the researcher “Higor Botelho” in “Qualis (\d+)”$/) { int arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
When(~/^I ask to show the articles that weren’t scored$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^I should see a list containing the name of the article that wasn’t scored$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}


