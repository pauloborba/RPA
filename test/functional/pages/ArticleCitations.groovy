package pages

/**
 * Created by rbb3 on 30/11/16.
 */
class ArticleCitations extends InternationalizedPage {
    static url = "/RPA/article/articleCitations/"

    static at = {
        title ==~ helperMsg.getMessage('default.articlecitations.label', 'ArtigoCitacoes')
    }

    void fillTitle(String title) {
        $('input[name="article"]').value(title)
    }

    void select() {
        $('input[type="submit"]').click()
    }

    def getTitleValue() {
        return $('input[name="article"]').value()
    }

    def getCitationValue() {
        return $('input[name="citations"]').value()
    }
}
