package pages

import geb.Page

/**
 * Created by dgmneto on 07/11/16.
 */
class Qualis20122ShowPage extends AbstractQualisShowPage {
    static url = 'qualis/show/5'

    static at = {
        title ==~ helperMsg.getMessage('default.show.label', '2012.2')
    }
}
