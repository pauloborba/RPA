package pages

import geb.Page

/**
 * Classe que extende de geb.Page para a interação com a view Edit de Qualis
 * Created by dgmneto on 07/11/16.
 */
class QualisEditPage extends Page{

    static url = '/qualis/edit/5'

    static at = {
        title ==~ 'Editar 2012'
    }

    def fillForm(String title, String path) {
        $('form')['title'] = title
        $('form')['qualis-sheet'] = path
    }

    def submitForm() {
        $('input.save').click()
    }

}
