package pages

import geb.Page

/**
 * Created by ajgan on 05/11/16.
 */
class removePage extends Page {
    static url = "researcher/removeStep1"
    static at={
        title==~"Remove Screen"
    }

    def select(String cpf){
        $("form").ResearcherSelector = cpf
    }

    def selectRemove() {
        $("form").ResearcherRemove.click()
    }

    def fillSearch(String text) {
        $("form").typed = text
    }

    def hasError() {
        $("div.message").text()
    }
}
