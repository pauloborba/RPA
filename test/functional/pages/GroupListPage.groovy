package pages

import geb.Page

class GroupListPage extends Page{
    static url = "/RPA/researchGroup/index"

    static at = {
        title ==~ /ResearchGroup List/
    }
}
