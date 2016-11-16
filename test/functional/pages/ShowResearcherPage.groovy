package pages

import geb.Page

class ShowResearcherPage extends Page {
    static url = "RPA/researcher/show"

    static at = {
        title ==~ /Ver Pesquisador/ || title ==~ /Show Researcher/
    }

    def findAcceptedMsg(){
        assert $("div", class: "message").text() == "O pesquisador abaixo foi registrado com sucesso" ||
                $("div", class: "message").text() == "The researcher below was successfully registered"
    }

    boolean checkName(String nome){
        return $("span", id: "name-value").text() == nome
    }

    boolean checkCpf(String cpf){
        return  $("span", id: "cpf-value").text() == cpf
    }

    boolean areThereArticles() {
        return $("span", id: "articles-value").text() != null
    }
}
