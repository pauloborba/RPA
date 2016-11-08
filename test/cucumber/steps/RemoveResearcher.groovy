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

Given(~/^Os pesquisadores de CPF "([^"]*)" e "([^"]*)" estão cadastrados$/) { String cpf1, String cpf2 ->
    ResearcherController rc = new ResearcherController()
    rc.params<<[cpf:cpf1, name:cpf1]
    rc.create()
    rc.save()
    rc.response.reset()
    rc.params<<[cpf:cpf2, name:cpf2]
    rc.create()
    rc.save()
    rc.response.reset()
    Researcher re1 = Researcher.findByCpf(cpf1)
    Researcher re2 = Researcher.findByCpf(cpf2)
    boolean condition =  (re1 != null) && (re2 != null)
    assert condition
}
When(~/^Eu seleciono na lista os pesquisadores de CPF "([^"]*)" e "([^"]*)"$/) { String cpf1, String cpf2 ->
    at removePage
    page.select(cpf1)
    page.select(cpf2)
}
And(~/^Seleciono para remover$/) { ->
    at removePage
    page.selectRemove()
}
Then(~/^Os pesquisadores de CPF "([^"]*)" e "([^"]*)" são removidos da lista de pesquisadores$/) { String cpf1, String cpf2 ->
    Researcher re1 = Researcher.findByCpf(cpf1)
    Researcher re2 = Researcher.findByCpf(cpf2)
    boolean condition = (re1 == null) && (re2 == null)
    assert condition
}
Given(~/^O pesquisador de CPF "([^"]*)" está cadastrado e pertence apenas aos grupos "([^"]*)" e "([^"]*)"$/) { String cpf, String g1, String g2 ->
    boolean p1, p2 = false
    Researcher re = Researcher.findByCpf(cpf)
    Group gr1 = Group.findByName(g1)
    int tam1 = gr1.researchers.size()
    for (int i =0; i<tam1; i++ ) {
        if (gr1.researchers[i].cpf == re.cpf)
            p1 = true

    }
    Group gr2 = Group.findByName(g2)
    int tam2 = gr2.researchers.size()
    for (int i =0; i<tam2; i++ ) {
        if (gr2.researchers[i].cpf == re.cpf)
            p2 = true
    }

    boolean condition = (re != null) && (p1 && p2)
    assert condition
}
When(~/^Eu marco o CPF "([^"]*)" e seleciono a opção de remover$/) { String cpf ->
    page.select(cpf)
    page.selectRemove()
}
Then(~/^A remoção é concluída e o CPF "([^"]*)" também é removido do grupo "([^"]*)" e do grupo "([^"]*)"\.$/) { String cpf1, String g1, String g2 ->
    boolean p1, p2 = false
    Researcher re = Researcher.findByCpf(cpf1)
    Group gr1 = Group.findByName(g1)
    int tam1 = gr1.researchers.size()
    for (int i =0; i<tam1; i++ ) {
        if (gr1.researchers[i].cpf == cpf1)
            p1 = true

    }
    Group gr2 = Group.findByName(g2)
    int tam2 = gr2.researchers.size()
    for (int i =0; i<tam2; i++ ) {
        if (gr2.researchers[i].cpf == re.cpf)
            p2 = true
    }

    boolean condition = (re == null) && (!p1 && !p2)
    assert condition
}
Given(~/^Estou na tela de remoção de pesquisador$/) { ->
    to removePage
    at removePage
}
And(~/^Eu não selecionei nenhum pesquisador da lista$/) { ->
    page.select(null)
}
When(~/^Eu seleciono a opção de remover$/) { ->
    at removePage
    page.selectRemove()
}
Then(~/^A remoção é rejeitada e uma mensagem é exibida dizendo que eu preciso escolher pelo menos um pesquisador para poder remover alguém$/) {
    ->
    at removePage
    assert page.hasError()
}
And(~/^Eu informei no campo de busca o CPF "([^"]*)"$/) { String cpf ->
    at removePage
    page.fillSearch(cpf)
}
Then(~/^A busca retorna null para o CPF "([^"]*)" e uma mensagem é exibida dizendo que o pesquisador que eu pretendo remover não foi encontrado\.$/) { String cpf ->
    Researcher re = Researcher.findByCpf(cpf)
    assert re == null
}