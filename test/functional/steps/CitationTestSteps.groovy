package steps

import jline.internal.Log
import rpa.Article
import rpa.GoogleScholarService

/**
 * Created by rbb3 on 05/11/16.
 */
class CitationTestSteps {

    static public boolean compareCitationAmount(String article, String researcher) {
        ArticleSteps articleSteps = new ArticleSteps()
        Article art = articleSteps.findArticle(article)

        if (art == null) {
            return false
        }

        int initialCitationAmount = art.citationAmount
        System.out.println(initialCitationAmount+"*")
        return initialCitationAmount == findCitations(article, researcher)
    }

    static public int findCitations(String article, String researcher) {
        Article art = (new ArticleSteps()).findArticle(article)
        GoogleScholarService gs = new GoogleScholarService()
        List<Article> list = new ArrayList<Article>()
        list.add(art)
        gs.findCitations(list)
        System.out.println(list.get(0).citationAmount+"+")
        return list.get(0).citationAmount
    }

    static public boolean informationStored(String article, String citations) {
        Article art = (new ArticleSteps()).findArticle(article)
        return art.citationAmount == citations.toInteger()
    }

}
