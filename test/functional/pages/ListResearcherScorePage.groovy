package pages

class ListResearcherScorePage extends InternacionalizedPage{
    static url = "/RPA/researcherScore/index/"

    static at = {
        title ==~ helperMsg.getMessage('default.list.label', 'ResearcherScore')
    }

    boolean Click(String res){
        $("a", text:res).click()
    }

}
