package pages

import geb.Page
import rpa.Qualis

/**
 * Created by dgmneto on 07/11/16.
 */
class QualisListPage extends PageWithI18nSupport{
    static url = 'qualis'

    static at = {
        title ==~ helperMsg.getMessage('default.list.label', 'Qualis')
    }

    boolean selectCreateNewQualis() {
        $("a.create").click()
    }

    boolean qualisExist(String title) {
        def table = $('table')
        def rows = table.find('tbody').find('tr')
        boolean val = false
        for(int i = 0; i < rows.size() && !val; ++i)
            if(rows[i].find('td').text() == title)
                val = true
        return val
    }

    def clickByTitle(String title) {
        def table = $('table')
        def rows = table.find('tbody').find('td')
        def link = rows.find('a', text: title)
        link.click()
    }

}
