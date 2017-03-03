package pages

import geb.Page

class CreateResearcherPage extends PageWithI18nSupport {
    private static final dirFiles = "test/functional/steps/"
    static url = "/RPA/researcher/create/"

    static at =  {
        def researcherlabel = helperMsg.getMessage('researcher.label')
        title == helperMsg.getMessage('default.create.label', researcherlabel)
    }

    void saveOrUpdateResearcherWithFile(String filename){
        def file = new File(dirFiles+filename)
        $("form").file = file.getAbsolutePath()
        $("form").create().click()
    }

    def createResearcherWithFile(String filename){
        def file = new File(dirFiles+filename)
        $("form").file = file.getAbsolutePath()
        $("input", type: "submit").click()
    }

    def findInvallidMsg(){
        assert $("div", class: "message").text() == helperMsg.getMessage('researcher.file.invalid')
    }

    def findEmptyMsg(){
        assert $("div", class: "message").text() == helperMsg.getMessage('researcher.file.empty')
    }

}
