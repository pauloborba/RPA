import rpa.Qualis
import rpa.QualisAvaliation
import steps.QualisTestDataAndOperations

import static cucumber.api.groovy.EN.*

Given(~'^não existe um qualis chamado "([^"]*)" cadastrado no sistema$') {String title ->
    assert !qualisExists(title)
}

When(~'^o sistema recebe uma submissão para cadastrar um qualis chamado "([^"]*)" com o arquivo "([^"]*)"$') { String title, String filename ->
    QualisTestDataAndOperations.createQualis(title, filename)
}

Then(~'^o sistema cadastra o qualis chamado "([^"]*)" com ([0-9]*) avaliações$') { String title, int count ->
    assert qualisExists(title)
    def qualisAvaliations = QualisAvaliation.findAllByQualis(Qualis.findByTitle(title))
    assert qualisAvaliations.size() == count
}
And(~'^o qualis "([^"]*)" tem uma avaliação "([^"]*)" para o veículo "([^"]*)" de ISSN "([^"]*)" no tema "([^"]*)"$') { String qualisTitle, String evaluation, String journalTitle, String issn, String subject ->
    def qualisAvaliationValues = [issn: issn, journal: journalTitle, evaluation: evaluation, subject: subject]
    checkQualisAndAvaliation(qualisTitle, qualisAvaliationValues)
}

Given(~'^existe um qualis chamado "([^"]*)" cadastrado no sistema$') { String title ->
    QualisTestDataAndOperations.createQualis(title, null)
    assert qualisExists(title)
}

def qualisExists(String title){
    return Qualis.findByTitle(title) != null
}

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