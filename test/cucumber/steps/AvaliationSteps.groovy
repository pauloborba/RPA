package cucumber.steps

import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^eu estou na página de avaliação de notas$/) { ->

    throw new PendingException()
}
When(~/^eu seleciono a pesquisadora cadastrada "([^"]*)" e o qualis "([^"]*)"$/) { String arg1, String arg2 ->

    throw new PendingException()
}
Then(~/^é mostrado na tela uma lista com a quantidade de publicações que a pesquisadora "([^"]*)" tem por nota no qualis de "([^"]*)"$/) { String arg1, String arg2 ->

    throw new PendingException()
}
Given(~/^o sistema contém informações sobre o pesquisador cadastrado "([^"]*)"$/) { String arg1 ->

    throw new PendingException()
}
And(~/^o pesquisador cadastrado "([^"]*)" possui publicações "([^"]*)", "([^"]*)" e "([^"]*)" nos periódicos "([^"]*)", "([^"]*)" e "([^"]*)" respectivamente$/) { String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7 ->

    throw new PendingException()
}
And(~/^o sistema contém o qualis "([^"]*)" com a nota "([^"]*)" para as publicações "([^"]*)" e o qualis "([^"]*)" com a nota "([^"]*)" para a publicação “N”$/) { String arg1, String arg2, String arg3, String arg4, String arg5 ->

    throw new PendingException()
}
When(~/^o sistema recebe a solicitação de avaliar o pesquisador "([^"]*)" pelo qualis "([^"]*)" e "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^o sistema retorna uma lista com a quantidade de publicações que o pesquisador "([^"]*)" tem por nota "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^o sistema não é modificado$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}