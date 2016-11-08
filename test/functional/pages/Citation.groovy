package pages

import geb.Page

/**
 * Created by rbb3 on 03/11/16.
 */
class Citation extends Page {
    static url = "/RPA/researcher/citations/"

    static at = {
        title ==~ /Ver Researcher/
    }

    def chooseResearcher(String name) {
        $("input[name='researcher']").value(name)
    }

    def chooseArticle(String title) {
        $("input[name='article']").value(title)
    }

    def select(str) {
        $("input[type='submit']")[0].click()
    }

    public int getResults() {
        return $("input[name='citations']")[0].value(true).toInteger()
    }

    def fieldNotNull() {
        if ($("input[name='citations']")[0].value(true) != "") {
            return true
        } else {
            return false
        }
    }
}
