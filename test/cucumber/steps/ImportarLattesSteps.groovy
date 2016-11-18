package steps

import cucumber.api.PendingException

import static cucumber.api.groovy.EN.*
import static steps.TestAndOperations.*
import rpa.Researcher
import pages.CreateResearcherPage
import pages.ShowResearcherPage

//---------- GIVENs ----------
//Funciona :)
Given(~/^O arquivo "([^"]*)" contém um pesquisador de CPF "([^"]*)" e nome "([^"]*)"\.$/) { String arquivo, String cpf, String nome ->
    def pesquisador = buildResearcherWithFile(arquivo)
    assert (pesquisador.cpf == cpf) && (pesquisador.name == nome)
}
//Funciona :)
And(~/^O pesquisador de CPF "([^"]*)" não está cadastrado$/) { String cpf ->
    assert (Researcher.findByCpf(cpf) == null)
}
//Funciona :)
And(~/^O arquivo "([^"]*)" contém o artigo "([^"]*)"\.$/) { String arquivo, String artigo ->
    assert searchArticleByTitle(buildResearcherWithFile(arquivo), artigo)
}
//Funciona :)
Given(~/^Estou na página de cadastrar pesquisadores$/) { ->
    to CreateResearcherPage
    at CreateResearcherPage
}
//Funciona :)
And(~/^O arquivo "([^"]*)" não contem artigos$/) { String arquivo ->
    def pesquisador = buildResearcherWithFile(arquivo)
    assert pesquisador.articles.size() < 1;
}
//---------- WHENs ----------
//Funciona :)
When(~/^Um arquivo de currículo de nome "([^"]*)" é importado$/) { String arquivo ->
    importFile(arquivo)
}
//Funciona :)
When(~/^Eu tento importar um arquivo de currículo de nome "([^"]*)"\.$/) { String arquivo ->
    at CreateResearcherPage
    page.createResearcherWithFile(arquivo)
}
//---------- THENs ----------
//Funciona :)
Then(~/^O pesquisador de nome "([^"]*)" e CPF "([^"]*)" é cadastrado no sistema$/) { String nome, String cpf ->
    assert compareResearcherWithCpfAndName(Researcher.findByCpf(cpf), cpf, nome)
}
//Funciona :)
And(~/^O artigo "([^"]*)" é adicionado ao currículo do pesquisador de CPF "([^"]*)"\.$/) { String artigo, String cpf ->
    assert searchArticleByTitle(Researcher.findByCpf(cpf), artigo)
}
//Funciona :)
Then(~/^Estou na página de exibir pesquisadores$/) { ->
    at ShowResearcherPage
}
//Funciona :)
And(~/^É exibido um aviso de que um pesquisador foi cadastrado$/) { ->
    at ShowResearcherPage
    page.findAcceptedMsg()
}
//Funciona :)
And(~/^São exibidos o nome do pesquisador "([^"]*)" e o CPF "([^"]*)".$/) { String nome, String cpf ->
    at ShowResearcherPage
    assert (page.checkName(nome) && page.checkCpf(cpf))
}
//Testando
And(~/^É exibido o artigo de título "([^"]*)", da revista "([^"]*)" e issn "([^"]*)".$/) { String titulo, String revista, String issn ->
    at ShowResearcherPage
    assert page.checkArticle(titulo, revista, issn)
}
//Funciona :)
And(~/^O pesquisador de CPF "([^"]*)" não possui artigos$/) { String cpf ->
    assert researcherHasNoArticles(Researcher.findByCpf(cpf))
}
//Funciona :)
And(~/^Não é exibido nenhum artigo$/) { ->
    at ShowResearcherPage
    assert page.areThereArticles() == false;
}