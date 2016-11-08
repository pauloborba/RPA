import rpa.Qualis
import rpa.QualisAvaliation
import steps.QualisTestDataAndOperations
import pages.*

import static cucumber.api.groovy.EN.*

Given(~/^não existe um qualis chamado "([^"]*)" cadastrado no sistema$/) {String title ->
    assert !qualisExists(title)
}

When(~/^o sistema recebe uma submissão para cadastrar um qualis chamado "([^"]*)" com o arquivo "([^"]*)"$/) { String title, String filename ->
    QualisTestDataAndOperations.createQualis(title, filename)
}

Then(~/^o sistema cadastra o qualis chamado "([^"]*)" com ([0-9]*) avaliações$/) { String title, int count ->
    assert qualisExists(title)
    def qualisAvaliations = QualisAvaliation.findAllByQualis(Qualis.findByTitle(title))
    assert qualisAvaliations.size() == count
}
And(~/^o qualis "([^"]*)" tem uma avaliação "([^"]*)" para o veículo "([^"]*)" de ISSN "([^"]*)" no tema "([^"]*)"$/)
{ String qualisTitle, String evaluation, String journalTitle, String issn, String subject ->
    def qualisAvaliationValues = [issn: issn, journal: journalTitle, evaluation: evaluation, subject: subject]
    checkQualisAndAvaliation(qualisTitle, qualisAvaliationValues)
}

Given(~/^existe um qualis chamado "([^"]*)" cadastrado no sistema$/) { String title ->
    QualisTestDataAndOperations.createQualis(title, null)
    assert qualisExists(title)
}

Given(~/^existe um qualis chamado "([^"]*)" que foi cadastrado com o arquivo "([^"]*)"$/)
{ String title, String filename ->
    QualisTestDataAndOperations.createQualis(title, filename)
    assert qualisExists(title)
}

When(~/^o sistema recebe uma requisição para deletar o qualis cujo título é "([^"]*)" contendo o seu id$/)
{ String title ->
    QualisTestDataAndOperations.deleteQualis(title)
}

Given(~/^eu estou na página de detalhe do qualis "([^"]*)" cadastrado com o arquivo "([^"]*)"$/)
{ String title, String filename ->
    to QualisListPage
    at QualisListPage
    page.selectCreateNewQualis()
    at QualisCreatePage
    def path = new File(".").getCanonicalPath() + File.separator + "test" + File.separator + "files" + File.separator + filename
    page.fillForm(title, path)
    page.submitForm()
}

When(~/^eu clicar em deletar na pagina de detalhes$/) { ->
    at QualisShowPage
    page.clickDelete()
}

Then(~/^eu estou na página de listagem de qualis e não existe um qualis chamado "([^"]*)"$/) { String title ->
    at QualisListPage
    assert !page.qualisExist(title)
}

When(~/^eu clico em editar na pagina de detalhes$/) { ->
    at QualisShowPage
    page.clickEdit()
    at QualisEditPage
}

And(~/^eu preencho o formulário de editar com título "([^"]*)" com o arquivo "([^"]*)"$/) { String title, String filename ->
    def path = new File(".").getCanonicalPath() + File.separator + "test" + File.separator + "files" + File.separator + filename
    page.fillForm(title, path)
}

And(~/^eu clico em atualizar$/) { ->
    page.submitForm()
}

Then(~/^o título do página é "([^"]*)"$/) { String title ->
    at QualisShowPage2
}

And(~/^o veículo "([^"]*)" está presente na página$/) { String journal ->
    page.findJournal(journal)
}

/**
 * Método para a verificação da existência de um qualis com um dado título
 * @param title
 * @return
 */
def qualisExists(String title) {
    return Qualis.findByTitle(title) != null
}

/**
 * Método que valida se uma avaliação foi cadastrada em um qualis
 * @param title
 * @param avaliation
 */
def checkQualisAndAvaliation(String title, avaliation) {
    def qualis = Qualis.findByTitle(title)
    def qualisAvaliations = QualisAvaliation.findAllByQualis(qualis)
    boolean val = false
    qualisAvaliations.each { it ->
        if(it.issn == avaliation.issn &&
                it.journal == avaliation.journal &&
                it.evaluation == avaliation.evaluation &&
                it.subject == avaliation.subject
        )
            val = true;
    }
    assert val
}