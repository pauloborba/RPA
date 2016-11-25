package steps

import pages.ShowResearcherPage
import rpa.Researcher
import rpa.Article
import pages.CreateResearcherPage
import rpa.UpdateLattes
import rpa.UpdateType

import static cucumber.api.groovy.EN.*

Given(~/^pesquisador  de nome "([^"]*)", cpf  "([^"]*)" está cadastrado no sistema importando o arquivo "([^"]*)"$/) {
    String name, String cpf, String filename ->
        TestAndOperations.importFile(filename)
        def researcher = Researcher.findByCpf(cpf)
        assert researcher != null
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
}

And(~/^o pesquisador de cpf "([^"]*)" tem "([0-9]*)" artigos$/) {
    String cpf, int amount ->
        def researcher = Researcher.findByCpf(cpf)
        assert researcher.articles.size() == amount
}

And(~/^o pesquisador de cpf "([^"]*)" tem o artigo "([^"]*)" do journal "([^"]*)" e issn "([^"]*)"$/) {
    String cpf, String title, String journal, String issn ->
        def researcher = Researcher.findByCpf(cpf)
        def article = new Article(title: title, journal: journal, issn: issn)
        assert researcher.articles.contains(article)
}

And(~/^o pesquisador de cpf "([^"]*)" não tem nenhuma atualização$/) {
    String cpf ->
        def researcher = Researcher.findByCpf(cpf)
        assert researcher.updates.size() == 0
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

Then(~/^sistema salva "([0-9]*)" atualizações no pesquisador de cpf "([^"]*)"$/) {
    int amount, String cpf ->
        def researcher = Researcher.findByCpf(cpf)
        assert researcher.updates.size() == amount
}

And(~/^O pesquisador de cpf "([^"]*)" tem uma atualização informando que o artigo "([^"]*)" foi adicionado$/) {
    String cpf, String title ->
        def researcher = Researcher.findByCpf(cpf)
        def updateLattes = new UpdateLattes(attribute: title, typeUpdate: UpdateType.ADD_ARTICLE)
        assert researcher.updates.contains(updateLattes)
}

And(~/^O pesquisador de cpf "([^"]*)" tem uma atualização informando que o artigo "([^"]*)" foi removido$/) {
    String cpf, String title ->
        def researcher = Researcher.findByCpf(cpf)
        def updateLattes = new UpdateLattes(attribute: title, typeUpdate: UpdateType.REMOVE_ARTICLE)
        assert researcher.updates.contains(updateLattes)
}

And(~/^o pesquisador de cpf "([^"]*)", nome "([^"]*)" tem "([0-9]*)" artigos$/) {
    String cpf, String name, int amountArticles ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher,cpf,name)
        assert researcher.articles.size() == amountArticles
}

Given(~/^o sistema não tem nenhum artigo com o titulo "([^"]*)" cadastrado$/) { String title ->
    assert Article.findByTitle(title) == null
}

And(~/^o arquivo "([^"]*)" tem o Pesquisador "([^"]*)", cpf "([^"]*)" e só tem o artigo "([^"]*)" com journal "([^"]*)" e issn "([^"]*)"$/) {
    String filename, String name, String cpf, String title, String journal, String issn ->
        def researcher = TestAndOperations.buildResearcherWithFile(filename)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.articles.size() == 1
        def article = researcher.articles[0]
        assert TestAndOperations.compareArticle(article,title,journal,issn)
}

And(~/^o artigo "([^"]*)" foi removido do sistema$/) {
    String title ->
        assert Article.findByTitle(title) == null
}

Then(~/^Sistema não armazena nenhuma nova atualização no pesquisador de cpf "([^"]*)"\.$/) {
    String cpf ->
        def researcher = Researcher.findByCpf(cpf)
        assert researcher.updates.size() == 0
}

Given(~/^pesquisador  de nome "([^"]*)", cpf "([^"]*)" foi cadastrado no sistema com o arquivo "([^"]*)"$/) {
    String name, String cpf, String filename ->
        to CreateResearcherPage
        at CreateResearcherPage
        page.createResearcherWithFile(filename)
        at ShowResearcherPage
        page.findResearcher(cpf, name)
}

And(~/^Eu posso ver que o pesquisador de cpf "([^"]*)" e nome "([^"]*)" tem "([^"]*)" artigos$/) {
    String cpf, String name, String amount ->
        at ShowResearcherPage
        page.findResearcher(cpf, name)
        page.findAmountArticles()
}

And(~/^Eu posso ver que o pesquisador de cpf "([^"]*)" e nome "([^"]*)" tem o artigo "([^"]*)" do journal "([^"]*)" e issn "([^"]*)"$/) {
    String cpf, String name, String title, String journal, String issn ->
        at ShowResearcherPage
        page.findResearcher(cpf, name)
        page.findArticle(title, journal, issn)
}

And(~/^Eu posso ver que o pesquisador de cpf "([^"]*)" e nome "([^"]*)" não tem atualizações$/) {
    String cpf, String name ->
        at ShowResearcherPage
        page.findResearcher(cpf, name)
        page.findAmountUpdates(0)
}

And(~/^Estou na página de importação de arquivo de pesquisador$/) { ->
    to CreateResearcherPage
    at CreateResearcherPage
}

When(~/^importo arquivo "([^"]*)"$/) {
    String filename ->
        page.createResearcherWithFile(filename)
}

Then(~/^Eu estou na pagina de visualização$/) { ->
    at ShowResearcherPage
}

And(~/^Eu vejo uma mensagem de confirmação$/) { ->
    at ShowResearcherPage
    page.findAcceptedMsg()
}

And(~/^É possível ver "([0-9]*)" atualizações$/) {
    int amount ->
        at ShowResearcherPage
        page.findAmountUpdates(amount)
}

And(~/^É possível ver o nome do artigo "([^"]*)" informando que ele foi adicionado\.$/) {
    String title ->
        at ShowResearcherPage
        assert page.findUpdateLattes(title, UpdateType.ADD_ARTICLE)
}

And(~/^São exibidas as informações do pesquisador "([^"]*)" e cpf "([^"]*)"$/) {
    String name, String cpf ->
        at ShowResearcherPage
        page.findResearcher(cpf,name)
}

And(~/^São exibidos "([0-9]*)" artigos$/) {
    int amount ->
        at ShowResearcherPage
        page.findAmountArticles(amount)
}

And(~/^É exibido o artigo "([^"]*)" com journal "([^"]*)" e issn "([^"]*)"$/) {
    String title, String journal, String issn ->
        at ShowResearcherPage
        page.findArticle(title, journal, issn)
}

And(~/^É possível ver o nome do artigo "([^"]*)" informando que ele foi removido\.$/) {
    String title ->
        at ShowResearcherPage
        assert page.findUpdateLattes(title, UpdateType.REMOVE_ARTICLE)
}