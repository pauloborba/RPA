package pages

/**
 * Created by rbb3 on 30/11/16.
 */
class ArticleCitations extends InternationalizedPage {
    static url = "/RPA/article/articleCitations/"

    static at = {
        title ==~ /ArticleCitations/
    }

    void fillTitle(String title) {
        $("form").article = title
    }

    void select() {
        $("input", name:"buscar").click()
    }

    String getTitleValue() {
        return $("form").article
    }

    int getCitationValue() {
        return $("form").citationsCount
    }
}
