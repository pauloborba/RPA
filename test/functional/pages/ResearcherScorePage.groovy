package pages

import geb.Page

class ResearcherScorePage extends Page{
    static url = "researcherScore/create"

    static at = {
        title ==~ /Create ResearcherScore/
    }
}
