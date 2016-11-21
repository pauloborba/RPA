package cucumber.steps

import pages.CreateGroupPage
import pages.CreateResearcherPage
import pages.GroupListPage
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


And(~/^O pesquisador "([^"]*)" com o cpf "([^"]*)" pertence ao grupo "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    to CreateResearcherPage
    at CreateResearcherPage
    page.CreateNewResearcher(arg1, arg2)
    def pesq = new Researcher[1]
    pesq[0] = Researcher.findByName(arg1)
    to CreateGroupPage
    at CreateGroupPage
    assert page.CreateNewGroup(arg3, pesq)
    assert ResearchGroup.findByName(arg3).researchers.contains(Researcher.findByName(arg1))
}

And(~/^Eu vejo os pesquisadores "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    to CreateGroupPage
    at CreateGroupPage
    def p1 = Researcher.findByName(arg1)
    def p2 = Researcher.findByName(arg2)
    def p3 = Researcher.findByName(arg3)
    assert page.ViewResearcher(arg1, p1.id)
    assert page.ViewResearcher(arg2, p2.id)
    assert page.ViewResearcher(arg3, p3.id)
}

When(~/^Eu seleciono a opcao de criar para o grupo "([^"]*)" com os pesquisadores  "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    def pesqs = new Researcher[3]
    pesqs[0] = Researcher.findByName(arg2)
    pesqs[1] = Researcher.findByName(arg3)
    pesqs[2] = Researcher.findByName(arg4)
    to CreateGroupPage
    at CreateGroupPage
    assert page.CreateNewGroup(arg1, pesqs)
}

Then(~/^Eu vejo que o grupo "([^"]*)" foi criado com os pesquisadores "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    to GroupListPage
    at GroupListPage
    assert ResearchGroup.findByName(arg1).researchers.contains(Researcher.findByName(arg2))
    assert ResearchGroup.findByName(arg1).researchers.contains(Researcher.findByName(arg3))
    assert ResearchGroup.findByName(arg1).researchers.contains(Researcher.findByName(arg4))
}