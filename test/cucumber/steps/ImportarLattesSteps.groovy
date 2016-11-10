import static cucumber.api.groovy.EN.*
import static steps.TestAndOperations.*
import rpa.Researcher

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
And(~/^O arquivo "([^"]*)" não contem artigos$/) { String arquivo ->
    def pesquisador = buildResearcherWithFile(arquivo)
    assert pesquisador.articles.size() < 1;
}
//Funciona :)
When(~/^Eu tento importar um arquivo de currículo de nome "([^"]*)"\.$/) { String arquivo ->
    importFile(arquivo)
}
//Funciona :)
Then(~/^O pesquisador de nome "([^"]*)" e CPF "([^"]*)" é cadastrado no sistema$/) { String nome, String cpf ->
    println(Researcher.findByCpf(cpf).name)
    assert compareResearcherWithCpfAndName(Researcher.findByCpf(cpf), cpf, nome)
}
//Funciona :)
And(~/^O artigo "([^"]*)" é adicionado ao currículo do pesquisador de CPF "([^"]*)"\.$/) { String artigo, String cpf ->
    assert searchArticleByTitle(Researcher.findByCpf(cpf), artigo)
}
And(~/^O pesquisador de CPF "([^"]*)" não possui artigos$/) { String cpf ->
    assert researcherHasNoArticles(Researcher.findByCpf(cpf))
}