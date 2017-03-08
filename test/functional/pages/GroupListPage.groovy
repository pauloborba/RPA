package pages

import geb.Page
import steps.InternationalizationHelper

class GroupListPage extends PageWithI18nSupport{
    static url = "/RPA/researchGroup/index"

    static at = {
        def researchgrouplabel = helperMsg.getMessage('researchGroup.label')
        title ==~ helperMsg.getMessage('default.list.label', researchgrouplabel)
    }
}
