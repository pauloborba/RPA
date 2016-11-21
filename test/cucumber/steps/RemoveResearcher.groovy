package steps

import cucumber.api.PendingException
import pages.removePage
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

    ResearcherController rc = new ResearcherController()
    rc.params<<[cpf:cpf1, name:nome1]
    rc.create()
    rc.save()
    rc.response.reset()
    rc.params<<[cpf:cpf2, name:nome2]
    rc.create()
    rc.save()
    rc.response.reset()
    Researcher re1 = Researcher.findByCpf(cpf1)
    Researcher re2 = Researcher.findByCpf(cpf2)
    boolean condition =  (re1 != null) && (re2 != null)

    assert condition
}

When(~/^Eu recebo a solicitação para remover os pesquisadores de CPF "([^"]*)" e "([^"]*)"$/) { String cpf1, String cpf2 ->
    ResearcherController rc = new ResearcherController()
    rc.params<<[typed:cpf1]
    rc.remove()
    rc.response.reset()
    rc.params<<[typed:cpf2]
    rc.remove()
    rc.response.reset()
}
Then(~/^Os pesquisadores de CPF "([^"]*)" e "([^"]*)" são removidos da lista de pesquisadores$/) { String cpf1, String cpf2 ->
    ResearcherController rc = new ResearcherController()
    Researcher re1 = Researcher.findByCpf(cpf1)
    Researcher re2 = Researcher.findByCpf(cpf2)
    assert (re1 == null) && (re2 == null)
}

Given(~/^Estou na tela de remoção de pesquisador$/) { ->
    to removePage
    at removePage
}
And(~/^Eu não selecionei nenhum pesquisador da lista$/) { ->
    page.fillSearch("")
}
When(~/^Eu seleciono a opção de remover$/) { ->
    at removePage
    page.selectRemove()
}
Then(~/^A remoção é rejeitada e uma mensagem é exibida dizendo que eu preciso escolher pelo menos um pesquisador para poder remover alguém$/) {
    ->
    at removePage
    assert page.hasErrors() != null
}
And(~/^Eu informei no campo de busca o CPF "([^"]*)"$/) { String cpf ->
    at removePage
    page.fillSearch(cpf)
}
Then(~/^A busca retorna null para o CPF "([^"]*)" e uma mensagem é exibida dizendo que o pesquisador que eu pretendo remover não foi encontrado\.$/) { String cpf ->
    Researcher re = Researcher.findByCpf(cpf)
    assert re == null
}
