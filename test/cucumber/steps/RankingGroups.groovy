package steps

import rpa.RankingGroupController
import rpa.ResearchGroup
import rpa.Researcher

import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ResearchGroup group
def ranking

Given(~/^O grupo "([^"]*)" existe/){
    String groupName ->
        group = new ResearchGroup(groupName, [])
        group.save flush: true
}

And(~/^Os pesquisadores "([^"]*)" e "([^"]*)" está cadastrado e está no grupo "([^"]*)"/){
    String pqName1, String pqName2, String groupName ->
        // def groupList = group.researchers
        Researcher pq1 = new Researcher(pqName1, '11111111111', [])
        Researcher pq2 = new Researcher(pqName2, '11111111112', [])

        pq1.save flush:true
        pq2.save flush:true

        group.researchers = [pq1, pq2]
        group.save flush: true
}

When(~/^E solicitado a listagem do grupo "([^"]*)"/){
    String groupName ->
        def controller = new RankingGroupController()
        ranking = controller.groupRanking(group)
}

Then(~/^E retornado o ranking do grupo "([^"]*)"/){
    String groupName ->
        assert ranking != null
        def max = ranking.max { it.grade }
        def min = ranking.min { it.grade }
        assert 0 == ranking.findIndexOf { it == max }
        assert ranking.findIndexOf { it == min } == (ranking.size() - 1)
}

And(~/^Os pesquisadores "([^"]*)" e "([^"]*)" sao listados/){
    String pq1, String pq2 ->
        assert ranking.find{it.name == pq1}
        assert ranking.find{it.name == pq2}
}

Given(~/^O grupo "([^"]*)" nao existe./) {
    String groupName ->
        group = ResearchGroup.findByName(groupName)
        assert group == null
}

def error
When(~/^ solicitado a listagem do grupo "([^"]*)"/) {
    String groupName ->
        try {
            def controller = new RankingGroupController()
            controller.groupRanking(groupName)
            error = null
        } catch(Exception e) {
            error = e.message
        }
}

Then(~/^E lancada uma excecao "([^"]*)"/) {
    String errorText
    assert error == errorText
}


Given(~/^Nao ha grupos cadastrados na base/) {

}

When(~/^E solicitado a listagem do ranking de grupos/) {

}

Then(~/^Aparece na tela uma mensagem de error/) {

}

When(~/^E solicitado a listagem do ranking do grupo "([^"]*)"/) {

}

Then(~/^Aparece na tela uma lista com o ranking dos pesquisadores/) {

}
