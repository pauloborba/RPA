package steps

import pages.ArticleCitations
import pages.ResearcherCitations
import rpa.Researcher
import rpa.Article

/**
 * Created by rbb3 on 01/11/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*
    Scenario: Integrity of data between the system and Google Scholar
*/
Given(~/^the researcher "([^"]*)" with cpf "([^"]*)" is stored by the system$/) { String name, String cpf ->
    ResearcherSteps.createResearcher(name, cpf)
    assert Researcher.findByName(name)
}
And(~/^the article with title "([^"]*)", issn "([^"]*)", journal "([^"]*)" from "([^"]*)" is stored by the system$/) { String title, String issn, String journal, String name ->
    ArticleSteps.CreateArticle(title, issn, journal)
    def researcher = Researcher.findByName(name)
    def article = Article.findByTitle(title)
    ResearcherSteps.updateResearcher(researcher, article)
    assert article && researcher.articles.contains(article)
}
And(~/^the system has no information about the citation amount to the article with title "([^"]*)".$/) { String title ->
    def cnt = Article.findByTitle(title).citationAmount
    assert cnt == 0
}
And(~/^the article with title "([^"]*)" has "([^"]*)" citations at Google Scholar$/) { String title, int citations ->
    def expected_citations = citations
    def article = Article.findByTitle(title)
    def amount_citations = CitationTestSteps.findCitations(article)
    assert expected_citations == amount_citations
}
When(~/^I try to find citations to "([^"]*)" from researcher "([^"]*)"\.$/) { String title, String name ->
    def art = Article.findByTitle(title)
    def res = Researcher.findByName(name)
    CitationTestSteps.findCitations(art)
    assert res.articles.contains(art)
}
Then(~/^the "([^"]*)" citations to the article "([^"]*)" are stored by the system$/) { int citations, String title ->
    assert CitationTestSteps.informationStored(title, citations)
}

/*
    Scenario: Data not found
*/
Then(~/^no citations to the article "([^"]*)" are stored by the system$/) { String title ->
    assert CitationTestSteps.informationStored(title, 0)
}

/*
    Scenario: Request for number of citations of a researcher
*/
And(~/^the researcher "([^"]*)" has only "([^"]*)" articles stored by the system: "([^"]*)" and "([^"]*)"\.$/) { String name, int citations, String title1, String title2 ->
    def article1 = Article.findByTitle(title1)
    def article2 = Article.findByTitle(title2)
    def researcher = Researcher.findByName(name)
    assert researcher.articles.contains(article1) &&
            researcher.articles.contains(article2) &&
            researcher.articles.size() == citations
}
When(~/^I try to find citations to "([^"]*)"$/) { String name ->
    def researcher = Researcher.findByName(name)
    CitationTestSteps.findCitationsResearcher(researcher)
}
Then(~/^the "([^"]*)" citations to the researcher "([^"]*)" are stored by the system$/) { int citations, String name ->
    assert CitationTestSteps.informationStoredResearcher(name, citations)
}

/*
    Scenario: Regular importing citations to article
*/
Given(~/^I am at the ArticleCitations Menu$/) { ->
    to ArticleCitations
    at ArticleCitations
}
When(~/^I select the article "([^"]*)"\.$/) { String title ->
    page.fillTitle(title)
}
And(~/^I ask to find citations to article$/) { ->
    at ArticleCitations
    page.select()
}
Then(~/^the number "([^"]*)" of citations to the article "([^"]*)" are shown$/) { String citations, String title ->
    waitFor (15, 0.1) {
        at ArticleCitations
        page.getCitationValue() != ""
    }
    def returnTitle = page.getTitleValue()
    def value = page.getCitationValue()
    assert returnTitle == title && value == citations
}

/*
    Scenario: Irregular importing citations to article
*/
Then(~/^the message "([^"]*)" is displayed$/) { String message ->
    if (message == "Article not found") {
        at ArticleCitations
    } else {
        at ResearcherCitations
    }
    assert page.getCitationValue() == message
}

/*
    Scenario: Regular importing citations to researcher
*/
Given(~/^I am at the ResearcherCitations Menu$/) { ->
    to ResearcherCitations
    at ResearcherCitations
}
When(~/^I select the researcher "([^"]*)"\.$/) { String name ->
    page.fillName(name)
}
And(~/^I ask to find citations to researcher/) { ->
    at ResearcherCitations
    page.select()
}