import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Estou na tela de cadastrar pesquisadores$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
When(~/^Eu tento importar um arquivo de currículo de nome "([^"]*)"$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^É exibida uma mensagem de erro avisando que o nome do arquivo precisa ser inserido$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^O sistema não é alterado$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^O arquivo de nome "([^"]*)" não é um arquivo de currículo válido$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^É exibida uma mensagem de erro avisando que "([^"]*)" não é um arquivo de currículo válido$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^O arquivo "([^"]*)" contém um pesquisador de CPF "([^"]*)" e nome "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^É exibida uma mensagem de erro avisando que "([^"]*)" possui um nome inválido$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^É exibida uma mensagem de erro avisando que "([^"]*)" possui um CPF inválido$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^O arquivo "([^"]*)" contém o artigo "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^É exibida uma mensagem de erro avisando que "([^"]*)" possui um artigo de nome inválido$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^O arquivo de nome "([^"]*)" não existe$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^É exibida uma mensagem de erro avisando que o arquivo "([^"]*)" não foi encontrado$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^O pesquisador de CPF "([^"]*)" não está cadastrado$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^É exibida uma mensagem de confirmação do cadastro com o nome "([^"]*)" e o CPF "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^É exibida uma lista com os artigos "([^"]*)" e "([^"]*)" avisando que eles foram adicionados$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^O pesquisador "([^"]*)" é cadastrado no sistema$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^O artigo "([^"]*)" é adicionado ao currículo do pesquisador$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^É exibido um aviso de que o pesquisador não possui nenhum artigo$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}