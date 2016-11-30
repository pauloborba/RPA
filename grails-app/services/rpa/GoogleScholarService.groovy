package rpa

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class GoogleScholarService {

    static def findCitations(List<Article> publications) {
        def totalCitations = 0
        for (publication in publications) {
            String publicationTitle = (publication.title).replace(" ", "+")
            String result
            def http = new HTTPBuilder()
            http.ignoreSSLIssues()
            http.request('https://scholar.google.com/', Method.GET, ContentType.TEXT) { req ->
                uri.path = '/scholar'
                uri.query = [hl: "en", q: publicationTitle]
                headers.'User-Agent' = "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36 Firefox/3.0.4"

                response.success = { resp, reader ->
                    assert resp.statusLine.statusCode == 200
                    result = reader.text
                }

                response.'404' = {
                    println 'Not found'
                }
            }
            System.out.println(result)
            if (result == null || result == "") {
                publication.citationAmount = 0
            } else {
                String auxiliar = countCitations(result)
                if (auxiliar == null || auxiliar == "0" || auxiliar == "N/E") {
                    publication.citationAmount = 0
                } else {
                    publication.citationAmount = auxiliar.toInteger()
                    totalCitations = totalCitations + auxiliar.toInteger()
                }
            }
        }
        totalCitations
    }

    private static String countCitations(String result) {
        String citations = ""
        try {
            def allCitations = result.split("Cited by")
            String wantedCitation = allCitations[1]
            citations = (wantedCitation.split("<"))[0]
            return citations
        } catch (e) {
            citations = "N/E"
            return citations
        }
    }

    static def updateCitations(Researcher researcher, int citations) {
        def cont = new ResearcherController()
        researcher.citationAmount = citations
        cont.save(researcher)
        cont.response.reset()
    }
}
