package pages

import geb.Page

/**
 * Classe que extende de geb.Page para a interação com a view Create de Qualis
 * Created by dgmneto on 07/11/16.
 */
class QualisCreatePage extends Page{
    static url = 'qualis/create'

    static at = {
        title ==~ /Criar qualis/
    }

    def fillForm(String title, String path) {
        $("form")['title'] = title
        $("form")['qualis-sheet'] = path
    }

    def submitForm() {
        $("input.save").click()
    }
}
