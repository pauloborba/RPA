package steps

import rpa.Researcher
import static cucumber.api.groovy.EN.*

Given(~/^pesquisador  de nome "([^"]*)", cpf  "([^"]*)", só tem o artigo "([^"]*)" do journal "([^"]*)" e issn "([^"]*)" e não tem atualizações está cadastrado no sistema$/) {
    String name, String cpf, String title, String journal, String issn ->
    TestAndOperations.createResearcher(
            TestAndOperations.addArticleToResearcher(TestAndOperations.buildResearcher(name,cpf),
                    TestAndOperations.buildArticle(title,journal,issn)))
    def researcher = Researcher.findByCpf(cpf)
    TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
    assert researcher.articles.size() == 1
    def article = researcher.articles[0]
    TestAndOperations.compareArticle(article, title, journal, issn)
}


And(~/^o arquivo "([^"]*)" tem o Pesquisador "([^"]*)" com cpf "([^"]*)" e tem dois artigos "([^"]*)" e  "([^"]*)" ambos com journal "([^"]*)"e issn "([^"]*)"$/) {
    String filename, String name, String cpf, String title1, String title2, String journal, String issn ->
    def researcher = TestAndOperations.buildResearcherWithFile(filename)
    assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
    assert researcher.articles.size() == 2
    def article1 = researcher.articles[0]
    def article2 = researcher.articles[1]
    assert (TestAndOperations.compareArticle(article1,title1,journal,issn) &&
            TestAndOperations.compareArticle(article2,title2,journal,issn)) ||
            (TestAndOperations.compareArticle(article1,title2,journal,issn) &&
                    TestAndOperations.compareArticle(article2,title1,journal,issn))

}

When(~/^eu tento importar arquivo "([^"]*)"$/) {
    String filename ->
    TestAndOperations.importFile(filename)
}

Then(~/^sistema salva um diff no pesquisador  de nome "([^"]*)" e cpf "([^"]*)" com a mensagem que o artigo "([^"]*)" foi adicionado$/) {
    String name, String cpf, String title ->
    def researcher = Researcher.findByCpf(cpf)
    assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
    assert researcher.diffs.size() == 1
    assert researcher.diffs[0].typeDiff == 1
}

And(~/^o pesquisador de cpf "([^"]*)", nome "([^"]*)" e tem dois artigos "([^"]*)" e  "([^"]*)" ambos com journal "([^"]*)" e issn "([^"]*)" está cadastrado$/) {
    String cpf, String name, String title1, String title2, String journal, String issn ->
    def researcher = Researcher.findByCpf(cpf)
    assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
    assert researcher.articles.size() == 2
    def article1 = researcher.articles[0]
    def article2 = researcher.articles[1]
    assert (TestAndOperations.compareArticle(article1,title1,journal,issn) &&
            TestAndOperations.compareArticle(article2,title2,journal,issn)) ||
            (TestAndOperations.compareArticle(article1,title2,journal,issn) &&
                    TestAndOperations.compareArticle(article2,title1,journal,issn))
}

