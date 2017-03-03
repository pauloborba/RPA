package pages

import geb.Page

/**
 * Classe que extende de geb.Page para a interação com a view Edit de Qualis
 * Created by dgmneto on 07/11/16.
 */
class QualisEditPage extends PageWithI18nSupport{

    static url = '/qualis/edit/5'

    static at = {
        title ==~ helperMsg.getMessage('default.edit.label', '2012')
    }

    def fillForm(String title, String path) {
        $('form')['title'] = title
        $('form')['qualis-sheet'] = path
    }

    def submitForm() {
        $('input.save').click()
    }

}
