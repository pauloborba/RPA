package cucumber.steps

import cucumber.api.PendingException
import pages.CreateGroupPage
import rpa.ResearchGroup
import rpa.ResearchGroupController
import rpa.Researcher
import rpa.ResearcherController

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def controladorGrupo = new ResearchGroupController()
def controladorPesquisador = new ResearcherController()

Given(~/^O sistema nao contem o grupo "([^"]*)" cadastrado no seu database$/) { String arg1 ->
    assert !ResearchGroup.findByName(arg1)
}

And(~/^O grupo "([^"]*)" contem "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    controladorPesquisador.createResearcher(arg2, "11111111111")
    controladorPesquisador.createResearcher(arg3, "11111111112")
    Researcher pesq1 = Researcher.findByName(arg2)
    Researcher pesq2 = Researcher.findByName(arg3)
    def pesquisadores = new Researcher[2]
    pesquisadores[0] = pesq1
    pesquisadores[1] = pesq2
    assert createResearcherGroup(arg1, controladorGrupo, pesquisadores)
    //assert ResearchGroup.findByName(arg1).researchers[0] == pesq1
}

When(~/^O sistema recebe uma submissao para adicionar o grupo "([^"]*)" com uma lista de  participantes contendo "([^"]*)", "([^"]*)" e o grupo de pesquisa "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    // Write code here that turns the phrase above into concrete actions
}

Then(~/^O sistema cria o grupo "([^"]*)", com "([^"]*)", "([^"]*)", "([^"]*)" e "([^"]*)" nele, no seu database$/) { String arg1, String arg2, String arg3, String arg4, String arg5 ->
    // Write code here that turns the phrase above into concrete actions
}

//GUI tests
Given(~/^Eu estou na pagina de Criacao de Grupos$/) { ->
}

And(~/^Eu vejo os grupos "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
}

And(~/^O pesquisador "([^"]*)" pertence ao grupo "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
}

When(~/^Eu seleciono a opcao de criar para o grupo "([^"]*)" com os grupos  "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    // Write code here that turns the phrase above into concrete actions
}

Then(~/^Eu vejo que o grupo "([^"]*)" foi criado com os pesquisadores "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    // Write code here that turns the phrase above into concrete actions
}

def static createResearcherGroup(String name, ResearchGroupController controlador, Researcher[] pesquisadores) {
    controlador.params << [name:name, researchers: pesquisadores]
    controlador.save()
    controlador.response.reset()
}
