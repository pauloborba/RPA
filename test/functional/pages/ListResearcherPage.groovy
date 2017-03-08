package pages

import geb.Page
import steps.InternationalizationHelper
import static steps.TestAndOperations.containsText

/**
 * Created by Alexsandro on 12/12/2016.
 */
class ListResearcherPage extends PageWithI18nSupport{
    static url = "/RPA/researcher/index/"

    static at = {
        def researcherlabel = helperMsg.getMessage('researcher.label')
        title ==~ helperMsg.getMessage('default.list.label', researcherlabel)
    }

    boolean existsResearcherWithCpf(String cpf) {
        return containsText(cpf, $("td").allElements())
    }
}
