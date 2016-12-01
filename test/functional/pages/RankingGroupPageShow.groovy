package pages

import geb.Page

class RankingGroupPageShow extends Page {
    static url = "/RPA/rankingGroup/index"

    static at = {
        def pageTitle = 'Ranking groups'
        title == pageTitle
    }

    def rankingListed (){
        def groups = $("td", class:"rankingTable")
        assert groups != null
    }

    def findErrorMsg() {
        def errorMsg = $("p", class:"error")
        assert errorMsg != null
    }
}