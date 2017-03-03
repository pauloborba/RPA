package pages

import geb.Page
import steps.InternationalizationHelper

class GroupListPage extends Page{
    InternationalizationHelper message = InternationalizationHelper.instance

    static url = "/RPA/researchGroup/index"

    static at = {
        title ==~ message.getMessage('default.list.label', 'ResearchGroup')
    }
}
