package steps

import pages.comparePage
import pages.resultComparePage
import rpa.GrupoPesquisadores
import rpa.GrupoPesquisadoresController
import static cucumber.api.groovy.EN.*
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
import geb.Page
import cucumber.api.PendingException


//Cenario 1

Given(~'^Os grupos "([^"]+)" e "([^"]+)" estão cadastrados no sistema$'){String nome1, String nome2->
    assert false
}

And(~'^"([^"]+)" tem nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') { String nome, int media, String tipo, String qualis ->
    createAndSaveGroup(nome)
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
    assert grupo!=null
    int k = grupo.revertQualis(tipo)
    assert grupo.getNotaStub(k,qualis,nome)==media
}
And(~'^"([^"]+)"  tem nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') {String nome, int media, String tipo,String qualis->
    createAndSaveGroup(nome)
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
    assert grupo!=null
    int k = grupo.revertQualis(tipo)
    assert grupo.getNotaStub(k,qualis,nome)==media
}

When(~'^Solicito a comparação usando Qualis "([^"]+)" entre "([^"]+)" e "([^"]+)"$') {String qualis,String nome1,String nome2->
    to comparePage

    nonNullGroups(nome1,nome2)

    page.dataToCompare(GrupoPesquisadores.findByNomeGrupo(nome1),GrupoPesquisadores.findByNomeGrupo(nome2),qualis)
}

Then(~'^A nota "([0-9]+)" do grupo "([^"]+)" fica verde e a nota "([0-9]+)" do grupo "([^"]+)" fica vermelha no critério "([^"]+)" do Qualis "([^"]+)"$') {int nota1,String nome1,int nota2,String nome2, String tipo,String qualis->
    at resultComparePage

    nonNullGroups(nome1,nome2)

    assert page.checkData(nota1,nota2,tipo)
}

//Cenario 2
Given(~'^Os grupos "([^"]+)" e "([^"]+)" estão cadastrados dentro do sistema$') {String nome1, String nome2->
    assert false
}

And(~'^"([^"]+)" possui nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') {String nome, int media,String tipo,String qualis->
    createAndSaveGroup(nome)
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
    assert grupo!=null
    int k = grupo.revertQualis(tipo)
    assert grupo.getNotaStub(k,qualis,nome)==media
}

And(~'^"([^"]+)" possui a nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') {String nome, int media,String tipo,String qualis->
    createAndSaveGroup(nome)
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
    assert grupo!=null
    int k = grupo.revertQualis(tipo)
    assert grupo.getNotaStub(k,qualis,nome)==media
}

When(~'^Solicito a comparação usando  Qualis "([^"]+)" entre "([^"]+)" e "([^"]+)"$') {String qualis, String nome1,String nome2->
    to comparePage
    nonNullGroups(nome1,nome2)
    page.dataToCompare(GrupoPesquisadores.findByNomeGrupo(nome1),GrupoPesquisadores.findByNomeGrupo(nome2),qualis)
}

Then(~'^A nota "([0-9]+)" do grupo "([^"]+)" e a nota "([0-9]+)" do grupo "([^"]+)" ficam azuis no critério "([^"]+)" do Qualis "([^"]+)"$') {int nota1,String nome1,int nota2,String nome2,String tipo,String qualis->
    at resultComparePage
    nonNullGroups(nome1,nome2)
    assert page.checkData(nota1,nota2,tipo)
}

//Cenario 3
Given(~'^O grupo "([^"]+)"  está cadastrado como um grupo no sistema$') {String nome1->
    assert false
}

When(~'^Solicito a comparação utilizando Qualis "([^"]+)" entre "([^"]+)" e "([^"]+)"$') {String qualis,String nome1, String nome2->
    createAndSaveGroup(nome1)
    createAndSaveGroup(nome2)
    nonNullGroups(nome1,nome2)

    to comparePage
    page.dataToCompare(GrupoPesquisadores.findByNomeGrupo(nome1),GrupoPesquisadores.findByNomeGrupo(nome2),qualis)
}

Then(~'^Aparece uma mensagem de erro dizendo que a comparação não pode ser realizada$') {->
    at resultComparePage
    assert page.errorMessage()!=null
}


//Cenario 4
Given(~'^Os grupos "([^"]+)" e  "([^"]+)" estão cadastrados no sistema$') {String nome1, String nome2->
    assert false
}

And(~'^"([^"]+)" tem nota "([0-9]+)" no "([^"]+)" do Qualis  "([^"]+)"$') {String nome, int media,String tipo,String qualis->
    createAndSaveGroup(nome)
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
    assert grupo!=null
    int k = grupo.revertQualis(tipo)
    assert grupo.getNotaStub(k,qualis,nome)==media
}

And(~'^"([^"]+)" tem nota "([0-9]+)" no "([^"]+)" do  Qualis "([^"]+)"$') {String nome, int media,String tipo,String qualis->
    createAndSaveGroup(nome)
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
    assert grupo!=null
    int k = grupo.revertQualis(tipo)
    assert grupo.getNotaStub(k,qualis,nome)==media
}

When(~'^Eu solicito a comparação usando Qualis "([^"]+)" entre "([^"]+)" e "([^"]+)"$') {String qualis, String nome1, String nome2->
    nonNullGroups(nome1,nome2)
    to comparePage
    page.dataToCompare(GrupoPesquisadores.findByNomeGrupo(nome1),GrupoPesquisadores.findByNomeGrupo(nome2),qualis)
}

Then(~'^"([^"]+)" continua com nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') {String nome, int media,String tipo,String qualis->
    checkNotaGrupo(nome,tipo,qualis,media)
}
And(~'^"([^"]+)" continua com  nota "([0-9]+)" no "([^"]+)" do Qualis "([^"]+)"$') {String nome, int media,String tipo,String qualis->
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
    int k = grupo.revertQualis(tipo)
    assert grupo.getNotaStub(k,qualis,nome)==media
}
def nonNullGroups(nome1,nome2){
    def grupo = GrupoPesquisadores.findByNomeGrupo(nome1)
    assert grupo!=null
    def grupo2 = GrupoPesquisadores.findByNomeGrupo(nome2)
    assert grupo2!=null
}