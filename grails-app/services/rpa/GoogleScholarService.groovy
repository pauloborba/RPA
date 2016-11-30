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
            http.request('http://scholar.google.com.br/', Method.GET, ContentType.TEXT) { req ->
                uri.path = '/scholar'
                uri.query = [hl: "en", q: publicationTitle]
                headers.'User-Agent' = "Mozilla/5.0 Chrome/48.0.2564.82 Safari/537.36 Firefox/3.0.4 Travis/1.0"
                headers.Accept = 'application/json'

                response.success = { resp, reader ->
                    assert resp.statusLine.statusCode == 200
                    result = reader.text
                }

                response.'404' = {
                    println 'Not found'
                }
            }
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
            def allCitations = result.split("Citado por")
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
