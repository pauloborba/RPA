package steps

import rpa.Article
import rpa.ArticleController

/**
 * Created by rbb3 on 05/11/16.
 */

class ArticleSteps {

    static articles = [
            [
                    title: "Implementing distribution and persistence aspects with AspectJ",
                    journal: "ACM",
                    issn: "11111",
                    citationAmount: 377
            ],
            [
                    title: "Assessing fine-grained feature dependencies",
                    journal: "ACM",
                    issn: "22222",
                    citationAmount: 0
            ]
    ]

    static public def findArticle(String title) {
        articles.find { article ->
            article.title == title
        }
    }


    static public void createArticle(String title, String journal, String issn, String citations) {
        def cont = new ArticleController()
        cont.params << findArticle(title) << [title: title]

        if (journal != null) {
            cont.params["journal"] = journal
        }

        if (issn != null) {
            cont.params["issn"] = issn
        }

        if (citations != null) {
            cont.params["citationAmount"] = citations.toInteger()
        }

        cont.request.setContent(new Byte[1000])
        cont.create()
        cont.save()
        cont.response.reset()
    }

    static public boolean isArticleStored(article, title) {
        def sArticle = Article.findByTitle(title)
        def hasArticle = true
        if (sArticle != null && article != null) {
            sArticle.each { key, data ->
                hasArticle = article."$key" == data
            }
            return hasArticle
        } else {
            return true
        }
    }
}