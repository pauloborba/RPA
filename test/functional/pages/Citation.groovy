package pages

import geb.Page

/**
 * Created by rbb3 on 03/11/16.
 */
class Citation extends Page {
    static url = "researcher/citations"

    static at = {
        title ==~ /Citation/
    }

    static void chooseResearcher(String name) {
        $('textField[name=researcher]').value = name
    }

    static void chooseArticle(String title) {
        $('textField[name=article]').value = title
    }

    static void select(String str) {
        $('button[name=buscar]').click()
    }

    static int getResults() {
        return $('textField[name=result]').value.toInteger()
    }
}
