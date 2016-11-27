package pages

import geb.Page

/**
 * Classe que extende de geb.Page para a interação com a view Show de Qualis
 * Created by dgmneto on 07/11/16.
 */
class QualisShowPage extends PageWithI18nSupport {
    static url = 'qualis/show/4'

    static at = {
        title ==~ helperMsg.getMessage('default.show.label', '2012')
    }

    def clickDelete() {
        assert withConfirm(true) {
            $('input.delete').click()
        }
    }

    def clickEdit() {
        $('a.edit').click()
    }
}
