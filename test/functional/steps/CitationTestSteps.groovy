package steps

import rpa.Article
import rpa.GoogleScholar
import rpa.Researcher

/**
 * Created by rbb3 on 05/11/16.
 */
class CitationTestSteps {

    static public int findCitations(Article article) {
        GoogleScholar gs = new GoogleScholar()
        List<Article> list = new ArrayList<Article>()
        list.add(article)
        gs.findCitations(list)
        return list.get(0).citationAmount
    }

    static public int findCitationsResearcher(Researcher researcher) {
        GoogleScholar gs = new GoogleScholar()
        List<Article> list = new ArrayList<Article>()
        researcher.articles.each { article ->
            list.add(article)
        }
        def totalCitations = gs.findCitations(list)
        gs.updateCitations(researcher, totalCitations)
        return totalCitations
    }

    static public boolean informationStored(String title, int citations) {
        Article art = Article.findByTitle(title)
        return art.citationAmount == citations
    }

    static public boolean informationStoredResearcher(String name, int citations) {
        Researcher res = Researcher.findByName(name)
        return res.citationAmount == citations
    }

}
