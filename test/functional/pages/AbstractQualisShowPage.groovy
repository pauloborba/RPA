package pages

/**
 * Created by dgmneto on 05/12/16.
 */
abstract class AbstractQualisShowPage extends PageWithI18nSupport{

    abstract static url;

    abstract static at;

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
