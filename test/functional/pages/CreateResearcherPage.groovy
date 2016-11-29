package pages

import geb.Page

class CreateResearcherPage extends Page {
    private static final dirFiles = "test/functional/steps/"
    static url = "/RPA/researcher/create/"

    static at = {
        title ==~ /Create Researcher/
    }

    def createResearcherWithFile(String filename){
        def file = new File(dirFiles+filename)
        $("form").file = file.getAbsolutePath()
        $("input", type: "submit").click()
    }

    def findInvallidMsg(){
        assert $("div", class: "message").text() == "O arquivo selecionado é inválido" ||
                $("div", class: "message").text() == "The file chosen is invalid"
    }

    def findEmptyMsg(){
        assert $("div", class: "message").text() == "O arquivo está vazio" ||
                $("div", class: "message").text() == "There is nothing in the archive"
    }
}