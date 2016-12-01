package pages

/**
 * Created by Bruno on 03/11/2016.
 */
import geb.Page
import geb.Module
import rpa.GrupoPesquisadores

class ResultComparePage extends PageSupport {


    def checkData(int nota1,int nota2,String crit){
        def result=false
        if(nota2<nota1){
            $("table", id: 'absValues').find("tr", class: "dados").each{
                def greenCell
                def redCell
                def critCell

                critCell = $(it).find('td',class:"Criterio").text().toString()
                greenCell = $(it).find('td',class:"green").text().toString()
                redCell = $(it).find('td',class:"red").text().toString()

                if(nota1.toString()==greenCell && nota2.toString()==redCell && crit==critCell)result=true
            }
            return result
        }

        else if(nota2>nota1){
            $("table", id: 'absValues').find("tr", class: "dados").each{
                def greenCell
                def redCell
                def critCell

                critCell = $(it).find('td',class:"Criterio").text().toString()
                greenCell = $(it).find('td',class:"green").text().toString()
                redCell = $(it).find('td',class:"red").text().toString()

                if(nota2.toString()==greenCell && nota1.toString()==redCell && crit==critCell)result=true
            }
            return result
        }
        else{
            $("table", id: 'absValues').find("tr", class: "dados").each{
                def blueCell1
                def varBlueCell1
                def blueCell2
                def critCell

                critCell = $(it).find('td',class:"Criterio").text().toString()
                blueCell1 = $(it).find('td',class:"blue")
                varBlueCell1=blueCell1.text().toString()
                blueCell2 = blueCell1.next().text().toString()

                if(nota1.toString()==varBlueCell1 && nota2.toString()==blueCell2 && crit==critCell)result=true
            }
            return result
        }
    }

    def checkAvgData(int numPesq1, int nota1,int numPesq2,int nota2,String crit){
        def result=false
        if((nota1/numPesq1)<(nota2/numPesq2)) {
            $("table", id: 'avgValues').find("tr", class: "dados").each {
                def greenCell
                def redCell
                def critCell

                critCell = $(it).find('td',class:"Criterio").text().toString()
                greenCell = $(it).find('td',class:"green").text().toString()
                redCell = $(it).find('td',class:"red").text().toString()

                if((nota1/numPesq1).toString()==redCell && (nota2/numPesq2).toString()==greenCell && crit==critCell)result=true
            }
            return result
        }
        else if((nota1/numPesq1)>(nota2/numPesq2)) {
            $("#avgValues").find("tr", class: "dadosMedia").each {
                def greenCell
                def redCell
                def critCell

                critCell = $(it).find('td',class:"Criterio").text().toString()
                greenCell = $(it).find('td',class:"green").text().toString()
                redCell = $(it).find('td',class:"red").text().toString()

                if((nota1/numPesq1).toString()==greenCell && (nota2/numPesq2).toString()==redCell && crit==critCell)result=true
            }
            return result
        }
        else {
            $("table", id: 'avgValues').find("tr", class: "dados").each {
                def blueCell1
                def varBlueCell1
                def blueCell2
                def critCell

                critCell = $(it).find('td',class:"Criterio").text().toString()
                blueCell1 = $(it).find('td',class:"blue")
                varBlueCell1=blueCell1.text().toString()
                blueCell2 = blueCell1.next().text().toString()

                if((nota1/numPesq1).toString()==varBlueCell1 && (nota2/numPesq2).toString()==blueCell2 && crit==critCell)result=true
            }
            return result
        }
    }

    static url = "grupoPesquisadores/resultCompare"

    static at = {
        def titleMsg = interMessage.getMessage('default.resultCompare.label')
        title == titleMsg
    }

    def errorMessage(){
        $("div.message").text()
    }

}
