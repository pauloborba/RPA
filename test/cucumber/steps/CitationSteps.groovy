package steps

import cucumber.api.PendingException
import rpa.ArticleController
import rpa.Researcher
import rpa.Article
import rpa.ResearcherController

/**
 * Created by rbb3 on 01/11/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*
    Scenario: Integrity of data between the system and Google Scholar
*/
Given(~/^the researcher "([^"]*)" with cpf "([^"]*)" is stored by the system$/) { String name, String cpf ->
    ResearcherSteps.CreateResearcher(name, cpf)
    assert Researcher.findByName(name)
}
And(~/^the article with title "([^"]*)", issn "([^"]*)", journal "([^"]*)" from "([^"]*)" is stored by the system$/) { String title, String issn, String journal, String name ->
    ArticleSteps.CreateArticle(title, issn, journal)
    def researcher = Researcher.findByName(name)
    def article = Article.findByTitle(title)
    researcher.articles.add(article)
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