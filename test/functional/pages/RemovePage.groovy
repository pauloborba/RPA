package pages

import geb.Page
import rpa.*

/**
 * Created by ajgan on 05/11/16.
 */
class RemovePage extends AuxiliarPage {
    static url = "researcher/removeStep1"

    static at={
        def titlemsg = helperMsg.getMessage('default.removeScreen.label')
        title == titlemsg
    }

    def select(String cpf){
        $("form").ResearcherSelector = cpf
    }

    def selectRemove() {
        $("input", type: "submit").click()
    }

    def fillSearch(String text) {
        $("form").typed = text
    }

    def hasErrors() {
        $("div.message").text()
    }

    def cpfExist(String cpf) {
        $("form").ResearchersSelector.contains(cpf)
    }
}
