package steps

import cucumber.api.PendingException
import pages.ShowResearcherPage
import rpa.Author
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

And(~/^o pesquisador de cpf "([^"]*)" tem o artigo "([^"]*)" do journal "([^"]*)", issn "([^"]*)" e dois autores "([^"]*)" e "([^"]*)"$/) {
    String cpf, String title, String journal, String issn, String author1, String author2 ->
        def researcher = Researcher.findByCpf(cpf)
        def authors = []
        authors << new Author(name: author1)
        authors << new Author(name: author2)
        def article = new Article(title: title, journal: journal, issn: issn, authors: authors)
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
        page.saveOrUpdateResearcherWithFile(filename)
        at ShowResearcherPage
        page.findResearcher(cpf, name)
}

And(~/^Eu posso ver que o pesquisador de cpf "([^"]*)" e nome "([^"]*)" tem "([^"]*)" artigos$/) {
    String cpf, String name, String amount ->
        at ShowResearcherPage
        page.findResearcher(cpf, name)
        page.findAmountArticles()
}

And(~/^Eu posso ver que o pesquisador de cpf "([^"]*)" e nome "([^"]*)" tem o artigo "([^"]*)" do journal "([^"]*)", issn "([^"]*)" e dois autores "([^"]*)" e "([^"]*)"$/) {
    String cpf, String name, String title, String journal, String issn, String author1, String author2 ->
        at ShowResearcherPage
        page.findResearcher(cpf, name)
        page.findArticleWithTwoAuthors(title, journal, issn, author1, author2)
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
        page.saveOrUpdateResearcherWithFile(filename)
}

Then(~/^Eu estou na pagina de visualização$/) { ->
    at ShowResearcherPage
}

And(~/^Eu vejo uma mensagem de confirmação$/) { ->
    at ShowResearcherPage
    page.findAcceptedMsg(true)
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

And(~/^É exibido o artigo "([^"]*)" com journal "([^"]*)", issn "([^"]*)" e dois autores "([^"]*)" e "([^"]*)"$/) {
    String title, String journal, String issn, String author1, String author2 ->
        at ShowResearcherPage
        page.findArticleWithTwoAuthors(title, journal, issn, author1, author2)
}

And(~/^É possível ver o nome do artigo "([^"]*)" informando que ele foi removido\.$/) {
    String title ->
        at ShowResearcherPage
        assert page.findUpdateLattes(title, UpdateType.REMOVE_ARTICLE)
}
And(~/^Eu vejo uma mensagem de alerta sobre o que foi alterado$/) { ->
    at ShowResearcherPage
        page.findAlertMsg()
}

And(~/^A mensagem de alerta informa "([0-9]*)" artigos foram alterados$/) {
    int amount ->
        at ShowResearcherPage
        page.findAmountAlertMsg(amount)
}

And(~/^A mensagem de alerta informa que o artigo "([^"]*)" foi adicionado$/) {
    String title ->
        at ShowResearcherPage
        page.findUpdateOnAlert(title, UpdateType.ADD_ARTICLE)
}

And(~/^A mensagem de alerta informa que o artigo "([^"]*)" foi removido$/) {
    String title ->
        at ShowResearcherPage
        page.findUpdateOnAlert(title, UpdateType.REMOVE_ARTICLE)
}
And(~/^pesquisador  de nome "([^"]*)", cpf "([^"]*)" foi atualizado o arquivo "([^"]*)"$/) {
    String name, String cpf, String filename ->
        to CreateResearcherPage
        at CreateResearcherPage
        page.saveOrUpdateResearcherWithFile(filename)
        at ShowResearcherPage
        page.findResearcher(cpf, name)
}
And(~/^Eu posso ver que o pesquisador de cpf "([^"]*)" e nome "([^"]*)" tem "([0-9]*)" atualização$/) {
    String cpf, String name, int amount ->
        at ShowResearcherPage
        page.findResearcher(cpf, name)
        page.findAmountUpdates(amount)
}
And(~/^Eu posso ver que o pesquisador de cpf "([^"]*)" e nome "([^"]*)" tem uma atualização informando que o artigo "([^"]*)" foi adicionado$/) {
    String cpf, String name, String title ->
        at ShowResearcherPage
        page.findResearcher(cpf, name)
        page.findUpdateLattes(title, UpdateType.ADD_ARTICLE)
}