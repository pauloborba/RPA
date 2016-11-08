package pages

import geb.Page

/**
 * Classe que extende de geb.Page para a interação com a view Show de Qualis
 * Created by dgmneto on 07/11/16.
 */
class QualisShowPage extends Page{
    static url = 'qualis/show/4'

    static at = {
        title ==~ 'Qualis 2012'
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
