package cucumber.steps

import cucumber.api.PendingException
import rpa.Article
import grails.test.GrailsUnitTestCase
import rpa.Avaliation
import rpa.AvaliationController
import rpa.Qualis
import rpa.Researcher
import rpa.QualisAvaliation

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//GUI
Given(~/^eu estou na pagina de avaliacao de notas$/) { ->

    throw new PendingException()
}
When(~/^eu seleciono a pesquisadora cadastrada "([^"]*)" e o qualis "([^"]*)"$/) { String arg1, String arg2 ->

    throw new PendingException()
}
Then(~/^eh mostrado na tela uma lista com a quantidade de publicacoes que a pesquisadora "([^"]*)" tem por nota no qualis de "([^"]*)"$/) { String arg1, String arg2 ->

    throw new PendingException()
}

//CONTROLLER

Given(~/^o pesquisador cadastrado "([^"]*)" possui publicacoes "([^"]*)", "([^"]*)" e "([^"]*)" nos periodicos "([^"]*)", "([^"]*)" e "([^"]*)" respectivamente$/) { String researcher, String publication1, String publication2, String publication3, String journal1, String journal2, String journal3 ->
    Article a1 = new Article([tittle: publication1, journal: journal1, issn: "10"])
    Article a2 = new Article([tittle: publication2, journal: journal2, issn: "11"])
    Article a3 = new Article([tittle: publication3, journal: journal3, issn: "12"])

    Set<Article> listArticles = new HashSet<Article>();
    listArticles.add(a1)
    listArticles.add(a2)
    listArticles.add(a3)

    a1.save flush: true
    a2.save flush: true
    a3.save flush: true

    Researcher r = new Researcher([name: researcher, cpf: "00011122233", articles: listArticles])
    r.save flush: true

}
And(~/^o sistema contem o qualis "([^"]*)" com a nota "([^"]*)" para a publicacao "([^"]*)" e o qualis "([^"]*)" com a nota "([^"]*)" para as publicacoes "([^"]*)" e "([^"]*)"$/) { String qualis1, String score1, String publication1, String qualis2, String score2, String publication2, String publication3 ->
    QualisAvaliation qAvaliation1 = new QualisAvaliation([journal: publication1, avaliation: score1])
    QualisAvaliation qAvaliation2 = new QualisAvaliation([journal: publication2, avaliation: score2])
    QualisAvaliation qAvaliation3 = new QualisAvaliation([journal: publication3, avaliation: score2])

    Set<QualisAvaliation> listQualisAvaliation = new HashSet<QualisAvaliation>()
    listQualisAvaliation.add(qAvaliation1)

    Set<QualisAvaliation> list2QualisAvaliation = new HashSet<QualisAvaliation>()
    list2QualisAvaliation.add(qAvaliation2)
    list2QualisAvaliation.add(qAvaliation3)

    qAvaliation1.save flush: true
    qAvaliation2.save flush: true
    qAvaliation3.save flush: true

    Qualis q1 = new Qualis([year: qualis1, avaliations: listQualisAvaliation])
    Qualis q2 = new Qualis([year: qualis2, avaliations: list2QualisAvaliation])

    q1.save flush: true
    q2.save flush: true

}
When(~/^o sistema recebe a solicitacao de avaliar o pesquisador "([^"]*)" pelo qualis "([^"]*)" e "([^"]*)"$/) { String researcher, String qualis1, String qualis2 ->

    Avaliation av1 = new Avaliation([researcher: Researcher.findByName(researcher), qualis: Qualis.findByYear(qualis1)])
    Avaliation av2 = new Avaliation([researcher: Researcher.findByName(researcher), qualis: Qualis.findByYear(qualis2)])

    AvaliationController controller = new AvaliationController()
    av1.categoryPoints = controller.CalculateScore(av1)
    av2.categoryPoints = controller.CalculateScore(av2)

    av1.save flush: true
    av2.save flush: true
}

Then(~/^o sistema retorna uma lista com a quantidade de publicacoes que o pesquisador "([^"]*)" tem por nota "([^"]*)" no qualis "([^"]*)"$/) { String researcher, String list, String qualis ->
    Avaliation av1 = Avaliation.findByResearcherAndQualis(Researcher.findByName(researcher), Qualis.findByYear(qualis))

    assert(av1.categoryPoints.equalsIgnoreCase(list))
}

And(~/^o sistema retorna outra lista com a quantidade de publicacoes que o pesquisador "([^"]*)" tem por nota "([^"]*)" no qualis "([^"]*)"$/) { String researcher, String list, String qualis ->
    Avaliation av2 = Avaliation.findByResearcherAndQualis(Researcher.findByName(researcher), Qualis.findByYear(qualis))
    assert(av2.categoryPoints.equalsIgnoreCase(list))
}