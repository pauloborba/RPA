package pages

import geb.Page
import steps.InternationalizationHelper
import static steps.TestAndOperations.containsText

/**
 * Created by Alexsandro on 12/12/2016.
 */
class ListResearcherPage extends Page{
    static url = "/RPA/researcher/index/"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        title ==~ helper.getMessage('default.list.label', "Researcher")
    }

    boolean existsResearcherWithCpf(String cpf) {
        return containsText(cpf, $("td").allElements())
    }
}
