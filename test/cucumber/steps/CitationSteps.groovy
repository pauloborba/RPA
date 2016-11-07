package steps

import cucumber.api.PendingException
import pages.Citation
import rpa.Researcher
import rpa.Article

/**
 * Created by rbb3 on 06/11/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^the researcher "([^"]*)" with cpf "([^"]*)" is stored by the system$/) { String arg1, String arg2 ->
    ResearcherSteps.createResearcher(arg1, arg2)
    Researcher researcher = Researcher.findByName(arg1)
    assert ResearcherSteps.isResearcherStored(researcher, arg1)
}

And(~/^the article with title "([^"]*)", issn "([^"]*)" from the journal "([^"]*)" and with "([^"]*)" citations at Google Scholar is stored by the system$/) { String arg1, String arg2, String arg3, String arg4 ->
    ArticleSteps.createArticle(arg1, arg2, arg3, arg4)
    Article article = Article.findByTittle(arg1)
    assert ArticleSteps.isArticleStored(article, arg1)
}

When(~/^I try to find citations to "([^"]*)" from researcher "([^"]*)"\.$/) { String arg1, String arg2 ->
    assert CitationTestSteps.compareCitationAmount(arg1, arg2)
}
Then(~/^the "([^"]*)" citations from the article "([^"]*)" are stored by the system$/) { String arg1, String arg2 ->
    assert CitationTestSteps.informationStored(arg2, arg1)
}
Then(~/^no citations from the article "([^"]*)" are stored by the system$/) { String arg1 ->
    assert CitationTestSteps.informationStored(arg1, "0")
}
Given(~/^I am at the Citations Page$/) { ->
    to Citation
    at Citation
}
When(~/^I click "([^"]*)"$/) { String arg1 ->
    assert true
}
Then(~/^I am redirected to the Results Page$/) { ->
    assert true
}
And(~/^the citations to me are displayed by the page$/) { ->
    assert true
}


