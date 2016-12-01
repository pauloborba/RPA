package steps

import pages.*
import rpa.GrupoPesquisadores
import rpa.GrupoPesquisadoresController
import rpa.Pesquisador
import rpa.PesquisadorController

import static cucumber.api.groovy.EN.*
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
//Cenario 1
Given(~'^Os pesquisadores "([^"]+)" de cpf "([^"]+)" e "([^"]+)" de cpf "([^"]+)" estão cadastrados no sistema$'){String nome1,String cpf1,String nome2,String cpf2->
    to CreateResearcherPage
    at CreateResearcherPage
    page.dataToAdd(nome1,cpf1)
    assert Pesquisador.findByCpf(cpf1)!=null
    to CreateResearcherPage
    at CreateResearcherPage
    page.dataToAdd(nome2,cpf2)
    assert Pesquisador.findByCpf(cpf2)!=null
}

And(~'^"([^"]+)" está cadastrado e tem o pesquisador "([^"]+)"$') { String nomeGrupo, String nomePesq ->
    to CreateResearcherGroupPage
    at CreateResearcherGroupPage
    def names=[]
    names.push(nomePesq)
    page.createInfo(nomeGrupo,names as String[])
    assert GrupoPesquisadores.findByNomeGrupo(nomeGrupo)!=null
}

And(~'^"([^"]+)" está cadastrado e tem os pesquisadores "([^"]+)" e "([^"]+)"$') { String nomeGrupo, String nomePesq1, String nomePesq2 ->
    to CreateResearcherGroupPage
    at CreateResearcherGroupPage
    def names=[]
    names.push(nomePesq1)
    names.push(nomePesq2)
    page.createInfo(nomeGrupo,names as String[])
    assert GrupoPesquisadores.findByNomeGrupo(nomeGrupo)!=null
}

And(~'^"([^"]+)" tem nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') { String nome, int media, String tipo, String qualis ->
    checkNotaGrupo(nome,tipo,qualis,media)
}

When(~'^Solicito a comparação usando Qualis "([^"]+)" entre "([^"]+)" e "([^"]+)"$') {String qualis,String nome1,String nome2->
    to ComparePage
    nonNullGroups(nome1,nome2)
    page.dataToCompare(GrupoPesquisadores.findByNomeGrupo(nome1),GrupoPesquisadores.findByNomeGrupo(nome2),qualis)
}

Then(~'^A nota "([0-9]+)" do grupo "([^"]+)" fica verde e a nota "([0-9]+)" do grupo "([^"]+)" fica vermelha no critério "([^"]+)" do Qualis "([^"]+)"$') {int nota1,String nome1,int nota2,String nome2, String tipo,String qualis->
    at ResultComparePage
    nonNullGroups(nome1,nome2)
    assert page.checkData(nota1,nota2,tipo)
}

And(~'^A nota media por pesquisador do grupo "([^"]+)" fica verde e a nota media por pesquisador do grupo "([^"]+)" fica vermelha no critério "([^"]+)" do Qualis "([^"]+)"$') {String nome1,String nome2, String tipo, String qualis ->
    at ResultComparePage
    nonNullGroups(nome1,nome2)
    def grupo1 = GrupoPesquisadores.findByNomeGrupo(nome1)
    def grupo2 = GrupoPesquisadores.findByNomeGrupo(nome2)
    assert page.checkAvgData(grupo1.pesquisadores.size(),grupo1.getGroupScore(tipo,qualis,nome1),grupo2.pesquisadores.size(),grupo2.getGroupScore(tipo,qualis,nome2),tipo)
}


//Cenario 2
Then(~'^A nota "([0-9]+)" do grupo "([^"]+)" e a nota "([0-9]+)" do grupo "([^"]+)" ficam azuis no critério "([^"]+)" do Qualis "([^"]+)"$') {int nota1,String nome1,int nota2,String nome2,String tipo,String qualis->
    at ResultComparePage
    nonNullGroups(nome1,nome2)
    assert page.checkData(nota1,nota2,tipo)
}

//Cenario 3
Given(~'^O pesquisador "([^"]+)" de cpf "([^"]+)" está cadastrado no sistema$') {String nome, String cpf->
    to CreateResearcherPage
    at CreateResearcherPage
    page.dataToAdd(nome,cpf)
    assert Pesquisador.findByCpf(cpf)
}

Then(~'^Aparece uma mensagem de erro dizendo que a comparação não pode ser realizada$') {->
    at ResultComparePage
    assert page.errorMessage()!=null
}

//Cenario 4
Given(~'^Os grupos "([^"]+)" e "([^"]+)" estão cadastrados no sistema$'){String nome1, String nome2->
    createAndSaveGroup(nome1)
    createAndSaveGroup(nome2)
    nonNullGroups(nome1,nome2)
}
When(~'^Faço a comparação usando Qualis "([^"]+)" entre "([^"]+)" e "([^"]+)"$') {String qualis,String nome1,String nome2->
    GrupoPesquisadoresController gc = new GrupoPesquisadoresController()
    gc.resultCompare(nome1,nome2,qualis)
}
Then(~'^"([^"]+)" continua com nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') {String nome, int media,String tipo,String qualis->
    checkNotaGrupo(nome,tipo,qualis,media)
}

def createAndSaveGroup(String nome){
    def grupoController = new GrupoPesquisadoresController()
    grupoController.params<<[nomeGrupo: nome]
    grupoController.create()
    grupoController.save()
    grupoController.response.reset()
}
def checkNotaGrupo(String nome,String tipo,String qualis,int media){
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
    assert grupo!=null
    assert grupo.getGroupScore(tipo,qualis,nome)==media
}
def nonNullGroups(String nome1, String nome2){
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome1)
    assert grupo!=null
    def grupo2 = GrupoPesquisadores.findByNomeGrupo(nome2)
    assert grupo2!=null
}
