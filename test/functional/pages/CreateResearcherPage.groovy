package pages

import geb.Page

class CreateResearcherPage extends PageWithI18nSupport {
    private static final dirFiles = "test/functional/steps/"
    static url = "/RPA/researcher/create/"

    static at =  {
        //getMessage é um método da super classe
        def researcherlabel = getMessage('researcher.label')
        def titlemsg = getMessage('default.create.label', researcherlabel)
        title == titlemsg
    }

    void createResearcherWithFile(String filename){
        def file = new File(dirFiles+filename)
        $("form").file = file.getAbsolutePath()
        $("form").create().click()
    }

}
