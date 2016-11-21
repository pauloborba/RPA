package cucumber.steps

import pages.CreateGroupPage
import rpa.ResearchGroup
import rpa.ResearchGroupController
import rpa.Researcher
import rpa.ResearcherController

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def controladorGrupo = new ResearchGroupController()
def controladorPesquisador = new ResearcherController()

//Controller test
Given(~/^O sistema nao contem o grupo "([^"]*)" cadastrado no seu database$/) { String arg1 ->
    assert !ResearchGroup.findByName(arg1)
}

And(~/^O grupo "([^"]*)" contem "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    def pesquisadores = new Researcher[2]
    pesquisadores[0] = new Researcher([name: arg2, cpf: "11111111111"])
    pesquisadores[1] = new Researcher([name: arg3, cpf: "11111111112"])
    def grupo = new ResearchGroup([name:arg1, researchers:pesquisadores])
    controladorGrupo.save(grupo)
    controladorGrupo.response.reset()
    assert ResearchGroup.findByName(arg1).researchers.contains(pesquisadores[0])
    assert ResearchGroup.findByName(arg1).researchers.contains(pesquisadores[1])
}

When(~/^O sistema recebe uma submissao para adicionar o grupo "([^"]*)" com uma lista de  participantes contendo "([^"]*)", "([^"]*)" e o grupo de pesquisa "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    def pesq = new Researcher[2]
    pesq[0] = new Researcher([name:arg2, cpf:"11111111113"])
    pesq[1] = new Researcher([name:arg3, cpf:"11111111114"])
    def grupo = new ResearchGroup([name:arg1, researchers: pesq])
    for(int i = 0; i < ResearchGroup.findByName(arg4).researchers.size(); i++) {
        grupo.researchers.add(ResearchGroup.findByName(arg4).researchers[i])
    }
    controladorGrupo.save(grupo)
    controladorGrupo.response.reset()
}

Then(~/^O sistema cria o grupo "([^"]*)", com "([^"]*)", "([^"]*)", "([^"]*)" e "([^"]*)" nele, no seu database$/) { String arg1, String arg2, String arg3, String arg4, String arg5 ->
    assert ResearchGroup.findByName(arg1).researchers.contains(Researcher.findByName(arg2))
    assert ResearchGroup.findByName(arg1).researchers.contains(Researcher.findByName(arg3))
    assert ResearchGroup.findByName(arg1).researchers.contains(Researcher.findByName(arg4))
    assert ResearchGroup.findByName(arg1).researchers.contains(Researcher.findByName(arg5))
}

//GUI tests
Given(~/^Eu estou na pagina de Criacao de Grupos$/) { ->
    to CreateGroupPage
    at CreateGroupPage
}

And(~/^Eu vejo os grupos "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    at CreateGroupPage

    //assert
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