package steps

import pages.ShowReseacherPage
import rpa.Researcher
import rpa.Article
import pages.CreateResearcherPage
import rpa.ResearcherController
import rpa.UpdateType

import static cucumber.api.groovy.EN.*

Given(~/^pesquisador  de nome "([^"]*)", cpf  "([^"]*)", só tem o artigo "([^"]*)" do journal "([^"]*)" e issn "([^"]*)" e não tem atualizações está cadastrado no sistema importando o arquivo "([^"]*)"$/) {
    String name, String cpf, String title, String journal, String issn, String filename ->
        TestAndOperations.importFile(filename)
        def researcher = Researcher.findByCpf(cpf)
        TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.articles.size() == 1
        def article = researcher.articles[0]
        TestAndOperations.compareArticle(article, title, journal, issn)
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
        Researcher.all
        TestAndOperations.importFile(filename)
        Researcher.all
}

Then(~/^sistema salva uma atualização no pesquisador de nome "([^"]*)" e cpf "([^"]*)" informando que o artigo "([^"]*)" foi adicionado$/) {
    String name, String cpf, String title ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.updates.size() == 1
        assert TestAndOperations.compareUpdateLattes(researcher.updates[0],title,UpdateType.ADD_ARTICLE)
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

Given(~/^o sistema não tem nenhum artigo com o titulo "([^"]*)" cadastrado$/) { String title ->
    assert Article.findByTitle(title) == null
}

And(~/^pesquisador  de nome "([^"]*)", cpf "([^"]*)", tem dois artigos "([^"]*)" e "([^"]*)" ambos com journal "([^"]*)" e issn "([^"]*)" e não tem atualizações está cadastrado no sistema importando o arquivo "([^"]*)"$/) {
    String name, String cpf, String title1, String title2, String journal, String issn, String filename ->

        TestAndOperations.importFile(filename)
        researcher = Researcher.findByCpf(cpf)
        TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.articles.size() == 2
        article1 = researcher.articles[0]
        article2 = researcher.articles[1]
        assert (TestAndOperations.compareArticle(article1,title1,journal,issn) &&
                TestAndOperations.compareArticle(article2,title2,journal,issn)) ||
                (TestAndOperations.compareArticle(article1,title2,journal,issn) &&
                        TestAndOperations.compareArticle(article2,title1,journal,issn))
}

And(~/^o arquivo "([^"]*)" tem o Pesquisador "([^"]*)", cpf "([^"]*)" e só tem o artigo "([^"]*)" com journal "([^"]*)" e issn "([^"]*)"$/) {
    String filename, String name, String cpf, String title, String journal, String issn ->
        def researcher = TestAndOperations.buildResearcherWithFile(filename)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.articles.size() == 1
        def article = researcher.articles[0]
        assert TestAndOperations.compareArticle(article,title,journal,issn)
}

And(~/^sistema salva uma atualização no pesquisador de nome "([^"]*)" e cpf "([^"]*)" informando que o artigo "([^"]*)" foi removido$/) {
    String name, String cpf, String title ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.updates.size() == 1
        assert TestAndOperations.compareUpdateLattes(researcher.updates[0],title, UpdateType.REMOVE_ARTICLE)

}

And(~/^o artigo "([^"]*)" foi removido do sistema$/) {
    String title ->
        assert Article.findByTitle(title) == null
}

And(~/^o pesquisador de cpf "([^"]*)", nome "([^"]*)" e só tem o artigo "([^"]*)" com journal "([^"]*)" e issn "([^"]*)" está cadastrado$/) {
    String cpf, String name, String title, String journal, String issn ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher,cpf,name)
        assert researcher.articles.size() == 1
        assert TestAndOperations.compareArticle(researcher.articles[0],title,journal,issn)
}

Then(~/^Sistema não armazena nenhuma nova atualização no pesquisador de cpf "([^"]*)"\.$/) {
    String cpf ->
        def researcher = Researcher.findByCpf(cpf)
        assert researcher.updates.size() == 0
}

And(~/^sistema salva duas atualizações no pesquisador de nome "([^"]*)" e cpf "([^"]*)" informando que o artigo "([^"]*)" foi removido e o artigo "([^"]*)" foi adicionado$/) {
    String name, String cpf, String title1, String title2 ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher,cpf,name)
        assert researcher.updates.size() == 2
        def updateLattes1 = researcher.updates[0]
        def updateLattes2 = researcher.updates[1]
        assert (TestAndOperations.compareUpdateLattes(updateLattes1, title1, UpdateType.REMOVE_ARTICLE) &&
                TestAndOperations.compareUpdateLattes(updateLattes2, title2, UpdateType.ADD_ARTICLE))||
                (TestAndOperations.compareUpdateLattes(updateLattes1, title2, UpdateType.ADD_ARTICLE) &&
                        TestAndOperations.compareUpdateLattes(updateLattes2, title1, UpdateType.REMOVE_ARTICLE))
}

Given(~/^pesquisador  de nome "([^"]*)", cpf  "([^"]*)", só tem o artigo "([^"]*)" do journal "([^"]*)" e issn "([^"]*)" e não tem atualizações foi cadastrado no sistema com o arquivo "([^"]*)"$/) {
    String name, String cpf, String title, String journal, String issn, String file ->
        to CreateResearcherPage
        at CreateResearcherPage
        page.createResearcherWithFile(file)
        at ShowReseacherPage
        page.compareReseacherWithArticle(name, cpf, title, journal, issn)
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
    at ShowReseacherPage
}

And(~/^Eu vejo uma mensagem de confirmação$/) { ->
    at ShowReseacherPage
    page.findAcceptedMsg()
}
And(~/^É possível ver o nome do artigo "([^"]*)" informando que ele foi adicionado\.$/) {
    String title ->
        at ShowReseacherPage
        assert page.findUpdateLattes(title, UpdateType.ADD_ARTICLE)
}

And(~/^São exibida as informações "([^"]*)", "([^"]*)" e o artigo "([^"]*)" e o artigo "([^"]*)" ambos com journal "([^"]*)" e issn "([^"]*)"$/) {
    String name, String cpf, String title1, String title2, String journal, String issn ->
        at ShowReseacherPage
        page.findReseacherWithTwoArticlesSameJournal(name, cpf, title1, title2, journal, issn)
}
