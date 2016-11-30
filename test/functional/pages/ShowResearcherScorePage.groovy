package pages

class ShowResearcherScorePage extends InternacionalizedPage {
    static url = "/RPA/researcherScore/show/3/"

    static at = {
        title ==~ helperMsg.getMessage('default.show.label', 'ResearcherScore')
    }

    boolean ShowingNotAvaliatedArticle(){
        $("a", text:"Not Avaliated: 1;")
    }

    boolean Listing(String res){
        $("a", text:res)
    }
}
