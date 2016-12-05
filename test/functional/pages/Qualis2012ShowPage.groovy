package pages

import geb.Page

/**
 * Classe que extende de geb.Page para a interação com a view Show de Qualis
 * Created by dgmneto on 07/11/16.
 */
class Qualis2012ShowPage extends AbstractQualisShowPage {
    static url = 'qualis/show/4'

    static at = {
        title ==~ helperMsg.getMessage('default.show.label', '2012')
    }
}
