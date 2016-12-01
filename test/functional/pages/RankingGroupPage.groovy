package pages

import geb.Page

class RankingGroupPage extends Page {
    static url = "/RPA/rankingGroup/show"

    static at = {
        def pageTitle = 'Listing ranking groups'
        title == pageTitle
    }

    def findErrorMsg() {
        def alertLastUpdates = $("p", class:"error")
        assert alertLastUpdates != null
    }

    def selectGroup(String groupName) {
        groupName = groupName + 'link'
        def groupRadio = $("a", name:groupName)
        assert groupRadio != null
        groupRadio.click()
    }

    def submit(){
        def button = $("a", name:"submitGroup")
        assert button != null
        button.click()
    }
}