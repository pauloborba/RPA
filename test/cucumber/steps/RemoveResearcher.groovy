package steps

import cucumber.api.PendingException
import pages.RemovePage
import rpa.Group
import rpa.Researcher
import rpa.ResearcherController

/**
 * Created by ajgan on 07/11/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Os pesquisadores de CPF "([^"]*)" e nome "([^"]*)" e CPF "([^"]*)" e nome "([^"]*)" estão cadastrados$/) {
    String cpf1, String nome1, String cpf2,  String nome2 ->

    createNewResearcher(nome1, cpf1)
    createNewResearcher(nome2, cpf2)
    Researcher re1 = Researcher.findByCpf(cpf1)
    Researcher re2 = Researcher.findByCpf(cpf2)

    assert (re1 != null) && (re2 != null)
}

When(~/^Eu recebo a solicitação para remover os pesquisadores de CPF "([^"]*)" e "([^"]*)"$/) { String cpf1, String cpf2 ->
    deleteResearcher(cpf1)
    deleteResearcher(cpf2)
}
Then(~/^Os pesquisadores de CPF "([^"]*)" e "([^"]*)" são removidos da lista de pesquisadores$/) { String cpf1, String cpf2 ->
    Researcher re1 = Researcher.findByCpf(cpf1)
    Researcher re2 = Researcher.findByCpf(cpf2)
    assert (re1 == null) && (re2 == null)
}

Given(~/^Estou na tela de remoção de pesquisador$/) { ->
    to RemovePage
    at RemovePage
}
And(~/^Eu não selecionei nenhum pesquisador da lista$/) { ->
    page.fillSearch("")
}
When(~/^Eu seleciono a opção de remover$/) { ->
    at RemovePage
    page.selectRemove()
}
Then(~/^A remoção é rejeitada e uma mensagem de erro é exibida$/) {
    ->
    at RemovePage
    assert page.hasErrors()
}
And(~/^Eu informei no campo de busca o CPF "([^"]*)"$/) { String cpf ->
    at RemovePage
    page.fillSearch(cpf)
}
Then(~/^O CPF "([^"]*)" não é encontrado e uma mensagem de erro é exibida$/) { String cpf ->
    at RemovePage
    assert !page.cpfExist(cpf) && page.hasErrors()
}

def createNewResearcher(String name, String cpf) {
    def rc = new ResearcherController()
    rc.params<<[name:name, cpf:cpf]
    rc.create()
    rc.save()
    rc.response.reset()
}

def deleteResearcher(String cpf) {
    def rc = new ResearcherController()
    rc.params<<[typed:cpf]
    rc.remove()
    rc.response.reset()
}