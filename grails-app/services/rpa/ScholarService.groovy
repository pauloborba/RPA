package rpa

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class ScholarService {

    static def findCitations(List<Article> publications) {
        def totalCitations = 0
        for (publication in publications) {
            String publicationTitle = (publication.title).replace(" ", "+")

            String result = makeRequest(publicationTitle)

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

    private static String makeRequest(publicationTitle) {
        String url = "https://scholar.google.com/scholar?hl=en&q="+publicationTitle;
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0");

        def reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        return response.toString()
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
