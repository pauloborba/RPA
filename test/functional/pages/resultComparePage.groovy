package pages

/**
 * Created by Bruno on 03/11/2016.
 */
import geb.Page
import geb.Module
import rpa.GrupoPesquisadores

class resultComparePage extends Page {

    def checkData(int nota1,int nota2,String crit){
        if(nota2<nota1){
            $("table").find("tr", class: "dados").each{
                it.find("td",class: "Criterio",text: crit)
                it.find("td",class: "green",text: nota1.toString())
                it.find("td",class: "red",text: nota2.toString())
            }
        }

        else if(nota2>nota1){
            $("table").find("tr", class: "dados").each{
                it.find("td",class: "Criterio",text: crit)
                it.find("td",class: "red",text: nota1.toString())
                it.find("td",class: "green",text: nota2.toString())
            }
        }
        else{
            $("table").find("tr", class: "dados").each{
                it.find("td",class: "Criterio",text: crit)
                it.find("td",class: "blue",text: nota1.toString())
                it.find("td",class: "blue",text: nota2.toString())
            }
        }
    }


    def titulo = "Resultados da Comparação"
    def titulo2 = "Compare Results"
    static url = "grupoPesquisadores/resultCompare"

    static at = {
        title ==~ titulo ||
        title ==~ titulo2
    }

    def errorMessage(){
        $("div.message").text()
    }

}
