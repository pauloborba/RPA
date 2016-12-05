package steps

import rpa.Article
import rpa.ArticleController

/**
 * Created by rbb3 on 05/11/16.
 */

class ArticleSteps {

    def static CreateArticle(String title, String journal, String issn) {
        def cont = new ArticleController()
        cont.create()
        cont.save(new Article(title: title, journal: journal, issn: issn))
        cont.response.reset()
    }
}
