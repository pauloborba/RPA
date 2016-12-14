package steps

import static cucumber.api.groovy.EN.*
import static steps.TestAndOperations.*
import rpa.Researcher
import pages.CreateResearcherPage
import pages.ListResearcherPage
import pages.ShowResearcherPage

//---------- GIVENs ----------
Given(~/^O arquivo "([^"]*)" contém um pesquisador de CPF "([^"]*)" e nome "([^"]*)"\.$/) { String arquivo, String cpf, String nome ->
    def pesquisador = buildResearcherWithFile(arquivo)
    assert (pesquisador.cpf == cpf) && (pesquisador.name == nome)
}
And(~/^O pesquisador de CPF "([^"]*)" não está cadastrado$/) { String cpf ->
    assert (Researcher.findByCpf(cpf) == null)
}
And(~/^Não é possível ver o pesquisador de CPF "([^"]*)"\.$/) { String cpf ->
    to ListResearcherPage
    at ListResearcherPage
    page.existsResearcherWithCpf(cpf) == false
}
And(~/^O arquivo "([^"]*)" contém (\d+) artigos$/) { String arquivo, int artigos ->
    assert buildResearcherWithFile(arquivo).articles.size() == artigos
}
And(~/^O arquivo "([^"]*)" contém o artigo "([^"]*)"\.$/) { String arquivo, String artigo ->
    assert searchArticleByTitle(buildResearcherWithFile(arquivo), artigo)
}
And(~/^O arquivo "([^"]*)" não é um arquivo de currículo$/) { String arquivo ->
    def pesquisador = buildResearcherWithFile(arquivo)
    assert pesquisador == null
}
Given(~/^O arquivo "([^"]*)" está vazio$/) { String arquivo ->
    assert archiveIsEmpty(arquivo)
}
//---------- WHENs ----------
When(~/^Um arquivo de currículo de nome "([^"]*)" é importado$/) { String arquivo ->
    importFile(arquivo)
}
When(~/^Eu tento importar um arquivo de currículo de nome "([^"]*)"\.$/) { String arquivo ->
    to CreateResearcherPage
    at CreateResearcherPage
    page.createResearcherWithFile(arquivo)
}
//---------- THENs ----------
Then(~/^O pesquisador de nome "([^"]*)" e CPF "([^"]*)" é cadastrado no sistema$/) { String nome, String cpf ->
    assert compareResearcherWithCpfAndName(Researcher.findByCpf(cpf), cpf, nome)
}
And(~/^O artigo "([^"]*)" é adicionado ao currículo do pesquisador de CPF "([^"]*)"\.$/) { String artigo, String cpf ->
    assert searchArticleByTitle(Researcher.findByCpf(cpf), artigo)
}
Then(~/^Estou na página de exibir pesquisadores$/) { ->
    at ShowResearcherPage
}
And(~/^É exibido um aviso de que um pesquisador foi cadastrado$/) { ->
    at ShowResearcherPage
    page.findAcceptedMsg()
}
And(~/^São exibidos o nome do pesquisador "([^"]*)" e o CPF "([^"]*)".$/) { String nome, String cpf ->
    at ShowResearcherPage
    assert (page.checkName(nome) && page.checkCpf(cpf))
}
And(~/^É exibido o artigo de título "([^"]*)", da revista "([^"]*)" e issn "([^"]*)".$/) { String titulo, String revista, String issn ->
    at ShowResearcherPage
    assert page.checkArticle(titulo, revista, issn)
}
And(~/^O pesquisador de CPF "([^"]*)" não possui artigos$/) { String cpf ->
    assert researcherHasNoArticles(Researcher.findByCpf(cpf))
}
And(~/^Não é exibido nenhum artigo$/) { ->
    at ShowResearcherPage
    assert page.areThereArticles() == false
}
And(~/^Nenhum pesquisador é cadastrado$/) { ->
    assert noResearchers()
}
Then(~/^Ainda estou na página de cadastrar pesquisadores$/) { ->
    at CreateResearcherPage
}
And(~/^É exibido um aviso de que o arquivo selecionado é inválido$/) { ->
    at CreateResearcherPage
    page.findInvallidMsg()
}
And(~/^É exibido um aviso de que o arquivo selecionado está vazio$/) { ->
    at CreateResearcherPage
    page.findEmptyMsg()
}