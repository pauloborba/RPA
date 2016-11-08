package pages

/**
 * Created by Bruno on 03/11/2016.
 */
import geb.Page
import rpa.GrupoPesquisadores

class comparePage extends Page {
    def titulo = "Comparar Grupo de Pesquisadores"
    def titulo2 = "Compare Researcher Group"
    static url = "grupoPesquisadores/compare"
    static at = {
        title ==~ titulo ||
        title ==~titulo2
    }

    def dataToCompare(GrupoPesquisadores g1, GrupoPesquisadores g2,String qualis) {

        $("form").grupoSelecionado2 = g2.getNomeGrupo()
        $("form").grupoSelecionado1 = g1.getNomeGrupo()
        $("form").qualis = qualis
        $("input", type: "submit").click()
    }

}
