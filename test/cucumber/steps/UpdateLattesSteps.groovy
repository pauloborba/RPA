package steps

import cucumber.api.PendingException
import pages.ShowReseacherPage
import rpa.Researcher
import rpa.Article
import pages.CreateResearcherPage
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
        assert researcher.diffs.size() == 0
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

Then(~/^sistema salva um diff no pesquisador  de nome "([^"]*)" e cpf "([^"]*)" informando que o artigo "([^"]*)" foi adicionado$/) {
    String name, String cpf, String title ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.diffs.size() == 1
        assert TestAndOperations.compareDiff(researcher.diffs[0],title,null,1)
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
    assert Article.findByTittle(title) == null
}

And(~/^pesquisador  de nome "([^"]*)", cpf "([^"]*)", tem dois artigos "([^"]*)" e "([^"]*)" ambos com journal "([^"]*)" e issn "([^"]*)" e não tem diff está cadastrado no sistema$/) {
    String name, String cpf, String title1, String title2, String journal, String issn ->
        def researcher = TestAndOperations.buildResearcher(name,cpf)
        def article1 = TestAndOperations.buildArticle(title1,journal,issn)
        def article2 = TestAndOperations.buildArticle(title2,journal,issn)
        researcher = TestAndOperations.addArticleToResearcher(researcher, article1)
        researcher = TestAndOperations.addArticleToResearcher(researcher, article2)
        TestAndOperations.createResearcher(researcher)
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

And(~/^sistema salva um diff no pesquisador  de nome "([^"]*)" e cpf "([^"]*)" informando que o artigo "([^"]*)" foi removido$/) {
    String name, String cpf, String title ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher, cpf, name)
        assert researcher.diffs.size() == 1
        assert TestAndOperations.compareDiff(researcher.diffs[0],title,null,2)

}

And(~/^o artigo "([^"]*)" foi removido do sistema$/) {
    String title ->
        assert Article.findByTittle(title) == null
}

And(~/^o pesquisador de cpf "([^"]*)", nome "([^"]*)" e só tem o artigo "([^"]*)" com journal "([^"]*)" e issn "([^"]*)" está cadastrado$/) {
    String cpf, String name, String title, String journal, String issn ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher,cpf,name)
        assert researcher.articles.size() == 1
        assert TestAndOperations.compareArticle(researcher.articles[0],title,journal,issn)
}

Then(~/^Sistema não armazena nenhum novo diff no pesquisador de cpf "([^"]*)"\.$/) {
    String cpf ->
        def researcher = Researcher.findByCpf(cpf)
        assert researcher.diffs.size() == 0
}

And(~/^sistema salva dois diff no pesquisador  de nome "([^"]*)" e cpf "([^"]*)" informando que o artigo "([^"]*)" foi removido e o artigo "([^"]*)" foi adicionado$/) {
    String name, String cpf, String title1, String title2 ->
        def researcher = Researcher.findByCpf(cpf)
        assert TestAndOperations.compareResearcherWithCpfAndName(researcher,cpf,name)
        assert researcher.diffs.size() == 2
        def diff1 = researcher.diffs[0]
        def diff2 = researcher.diffs[1]
        assert (TestAndOperations.compareDiff(diff1, title1, null, 2) &&
                TestAndOperations.compareDiff(diff2, title2, null, 1))||
                (TestAndOperations.compareDiff(diff1, title2, null, 1) &&
                        TestAndOperations.compareDiff(diff2, title1, null, 2))
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
And(~/^É posso ver o nome do artigo "([^"]*)" informando que ele foi adicionado\.$/) {
    String title ->
        at ShowReseacherPage
        assert page.findDiff(title, 1)
}

And(~/^É exibida as informações "([^"]*)", "([^"]*)" e o artigo "([^"]*)" e o artigo "([^"]*)" ambos com journal "([^"]*)" e issn "([^"]*)"$/) {
    String name, String cpf, String title1, String title2, String journal, String issn ->
        at ShowReseacherPage
        page.findReseacherWithTwoArticlesSameJournal(name, cpf, title1, title2, journal, issn)
}
