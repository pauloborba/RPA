package cucumber.steps

import cucumber.api.PendingException
import pages.CreateGroupPage
import rpa.ResearchGroup
import rpa.ResearchGroupController

/**
 * Created by lss on 05/11/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


Given(~/^O sistema não contém o grupo "([^"]*)" cadastrado no seu database\.$/) { String arg1 ->
    def controller = new ResearchGroupController()
    assert !ResearchGroup.findByName(arg1)
}

And(~/^O grupo "([^"]*)" contém "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    def group
    group = ResearchGroup.setName(arg1)
    group
    throw new PendingException()
}

When(~/^O sistema recebe uma submissão para adicionar o grupo "([^"]*)" com uma lista de  participantes contendo "([^"]*)", “Fernando Castor” e o grupo de pesquisa “Inteligência Artificial CIn”\.$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^O sistema cria o grupo "([^"]*)", com "([^"]*)", "([^"]*)", "([^"]*)" e "([^"]*)" nele, no seu database\.$/) { String arg1, String arg2, String arg3, String arg4, String arg5 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

//GUI tests

def group1
def group2
def group3

Given(~/^Eu estou na página de Criação de Grupos$/) { ->
    to CreateGroupPage
    at CreateGroupPage
}

And(~/^Eu vejo os grupos "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

And(~/^O pesquisador "([^"]*)" pertence ao grupo "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

When(~/^Eu seleciono a opção de criar para o grupo "([^"]*)" com os grupos  "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^Eu vejo que o grupo "([^"]*)" foi criado com os pesquisadores "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}