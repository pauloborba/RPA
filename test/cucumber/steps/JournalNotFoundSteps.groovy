package steps

import cucumber.api.PendingException

import pages.ResearcherScorePage
import rpa.Researcher
import rpa.ResearcherController
import rpa.Qualis
import rpa.QualisAvaliation

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


Given(~/^I am at the create researcher score page$/) {
    to ResearcherScorePage
    at ResearcherScorePage
}
And(~/^the qualis "([^"]*)" has avaliations for the journals entitled "([^"]*)", "([^"]*)" and "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^the researcher "([^"]*)" has only two articles: "([^"]*)" published at "([^"]*)" and Software Development" published at “Nature”$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
When(~/^I ask for the score of "([^"]*)" in the qualis "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^I go to the succesful creation page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^I can see that “(\d+)” article wasn’t scored because it’s journal wasn’t found$/) { int arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
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
