import cucumber.api.PendingException

import static cucumber.api.groovy.EN.*

Given(~/^the "([^"]*)" has no journal entitled “([^"]*)”$/) { String qualis, journal ->
    throw new PendingException()
}

And(~/^the researcher "([^"]*)" has an article entitled "([^"]*)" published at “([^"]*)”$/) { String researcher, article, journal ->
    throw new PendingException()
}

And(~/^every other article of the researcher “([^"]*)” is in the “([^"]*)”$/) { String researcher, qualis ->
    throw new PendingException()
}

When(~/^I ask for the score of "([^"]*)" in the "([^"]*)"$/) { String researcher, qualis ->
    throw new PendingException()
}

Then(~/^I can see that “([^"]*)” article wasn’t scored because it’s journal wasn’t found$/) { String score ->
    throw new PendingException()
}

Given(~/^I am at the score page for the researcher “([^"]*)” in “([^"]*)”$/) { String researcher, qualis ->
    throw new PendingException()
}

When(~/^I ask to show the articles that weren’t scored$/) {
    throw new PendingException()
}

Then(~/^I should see a list containing the name of the article that wasn’t scored$/) {
    throw new PendingException()
}