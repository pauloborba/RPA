package pages

import geb.Page
import steps.InternationalizationHelper

class CreateResearcherPage extends Page {
    private static final dirFiles = "test/functional/steps/"
    static url = "/RPA/researcher/create/"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        title ==~ helper.getMessage('default.create.label', "Researcher")
    }

    def createResearcherWithFile(String filename){
        def file = new File(dirFiles+filename)
        $("form").file = file.getAbsolutePath()
        $("input", type: "submit").click()
    }

    def findInvallidMsg(){
        InternationalizationHelper helper = InternationalizationHelper.instance
        assert $("div", class: "message").text() == helper.getMessage('researcher.file.invalid')
    }

    def findEmptyMsg(){
        InternationalizationHelper helper = InternationalizationHelper.instance
        assert $("div", class: "message").text() == helper.getMessage('researcher.file.empty')
    }
}