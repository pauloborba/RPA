package pages

import geb.Page

class CreateResearcherPage extends Page {
    private static final dirFiles = "test/functional/steps/"
    static url = "/RPA/researcher/create/"

    static at =  {
        title ==~ /Criar Pesquisador/ || title ==~ /Create Researcher/
    }

    void createResearcherWithFile(String filename){
        def file = new File(dirFiles+filename)
        $("form").file = file.getAbsolutePath()
        $("form").create().click()
    }

}
