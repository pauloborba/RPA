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

//Controller test1
Given(~/^O sistema nao contem o grupo "([^"]*)" cadastrado no seu database$/) { String arg1 ->
    assert !ResearchGroup.findByName(arg1)
}

And(~/^O grupo "([^"]*)" contem apenas "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    def pesquisadores = new Researcher[2]
    pesquisadores[0] = new Researcher([name: arg2, cpf: "11111111111"])
    pesquisadores[1] = new Researcher([name: arg3, cpf: "11111111112"])
    def grupo = new ResearchGroup([name:arg1, researchers:pesquisadores])
    controladorGrupo.save(grupo)
    controladorGrupo.response.reset()
    assert ResearchGroup.findByName(arg1).findResearcher(pesquisadores[0])
    assert ResearchGroup.findByName(arg1).findResearcher(pesquisadores[1])
    assert ResearchGroup.findByName(arg1).groupSizeEqualsTo(2)
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
    def grupo = ResearchGroup.findByName(arg1)
    assert grupo.findResearcher(Researcher.findByName(arg2))
    assert grupo.findResearcher(Researcher.findByName(arg3))
    assert grupo.findResearcher(Researcher.findByName(arg4))
    assert grupo.findResearcher(Researcher.findByName(arg5))
    assert grupo.groupSizeEqualsTo(4)
}

//Controller test2
When(~/^O sistema recebe uma submissao para adicionar o grupo "([^"]*)"$/) { String arg1 ->
    def grupo = new ResearchGroup([name:arg1])
    def controladorGrupo2 = new ResearchGroupController()
    controladorGrupo2.save(grupo)
    controladorGrupo2.response.reset()
}

Then(~/^O sistema nao cria um grupo "([^"]*)"$/) { String arg1 ->
    assert !ResearchGroup.findByName(arg1)
}

//Controller test3
Given(~/^O grupo "([^"]*)" nao tem o pesquisador "([^"]*)"$/) { String arg1, String arg2 ->
    def pesq = new Researcher[2]
    def grupo = new ResearchGroup([name: arg1, researchers: pesq])
    def pesquisador = new Researcher([name: arg2, cpf: "23232323232"])
    controladorGrupo.save(grupo)
    controladorGrupo.response.reset()
    assert !ResearchGroup.findByName(arg1).findResearcher(pesquisador)
}

When(~/^O sistema recebe uma submissao para adicionar "([^"]*)" ao grupo "([^"]*)"$/) { String arg1, String arg2 ->
    def grupo = ResearchGroup.findByName(arg2)
    def pesquisador = new Researcher([name: arg2, cpf: "23232323232"])
    grupo.researchers.add(pesquisador)
}

Then(~/^O grupo "([^"]*)" passa a ter "([^"]*)" entre seus integrantes$/) { String arg1, String arg2 ->
    assert ResearchGroup.findByName(arg1).findResearcher(Researcher.findByName(arg2))
}

//GUI test
Given(~/^Eu estou na pagina de Criacao de Grupos$/) { ->
    to CreateGroupPage
    at CreateGroupPage
}


And(~/^O pesquisador "([^"]*)" criado com arquivo "([^"]*)" pertence ao grupo "([^"]*)"$/) { String name, String filename, String groupname ->
    to CreateResearcherPage
    at CreateResearcherPage
    page.saveOrUpdateResearcherWithFile(filename)
    def pesq = new Researcher[1]
    pesq[0] = Researcher.findByName(name)
    to CreateGroupPage
    at CreateGroupPage
    assert page.createNewGroup(groupname, pesq)
    ResearchGroup.all
    Researcher.all
    def researcher = Researcher.findByName(name)
    def researchGroup = ResearchGroup.findByName(groupname)

    assert researchGroup.findResearcher(researcher)
}

And(~/^Eu vejo os pesquisadores "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    to CreateGroupPage
    at CreateGroupPage
    def p1 = Researcher.findByName(arg1)
    def p2 = Researcher.findByName(arg2)
    def p3 = Researcher.findByName(arg3)
    assert page.viewResearcher(arg1, p1.id)
    assert page.viewResearcher(arg2, p2.id)
    assert page.viewResearcher(arg3, p3.id)
}

When(~/^Eu seleciono a opcao de criar o grupo "([^"]*)" com os pesquisadores  "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    def pesqs = new Researcher[3]
    pesqs[0] = Researcher.findByName(arg2)
    pesqs[1] = Researcher.findByName(arg3)
    pesqs[2] = Researcher.findByName(arg4)
    to CreateGroupPage
    at CreateGroupPage
    assert page.createNewGroup(arg1, pesqs)
}

Then(~/^Eu vejo que o grupo "([^"]*)" foi criado com os pesquisadores "([^"]*)", "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    to GroupListPage
    at GroupListPage
    assert ResearchGroup.findByName(arg1).findResearcher(Researcher.findByName(arg2))
    assert ResearchGroup.findByName(arg1).findResearcher(Researcher.findByName(arg3))
    assert ResearchGroup.findByName(arg1).findResearcher(Researcher.findByName(arg4))
}