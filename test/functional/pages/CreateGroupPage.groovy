package pages

import geb.Page

class CreateGroupPage extends Page{
    static url = "/RPA/researchGroup/create"

    static at = {
        title ==~ /Create ResearchGroup/
    }

    boolean CreateNewGroup(String name) {
        
    }
}
