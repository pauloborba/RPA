package pages

import geb.Page

/**
 * Created by dgmneto on 07/11/16.
 */
class QualisShowPage2 extends Page{
    static url = 'qualis/show/5'

    static at = {
        title ==~ 'Qualis 2012.2'
    }

    def clickDelete() {
        assert withConfirm(true) {
            $('input.delete').click()
        }
    }

    def clickEdit() {
        $('a.edit').click()
    }

    def findJournal(String journal) {
        assert $('a.qualis-avaliation-link', text: journal).size() == 1
    }
}
