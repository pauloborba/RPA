package rpa

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class XmlExtractorService {
    def getResearcher(InputStream file){
        Researcher researcher = new Researcher()
        Document document = buildDocument(file)
        if(document == null){
            return document
        }
        if (document.getDocumentElement().getNodeName().equals("CURRICULO-VITAE")){
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    if (node.getNodeName().equals("DADOS-GERAIS")) {
                        researcher.name = node.getAttributes().getNamedItem("NOME-COMPLETO").getNodeValue();
                        researcher.cpf = node.getAttributes().getNamedItem("CPF").getNodeValue();
                    }
                    else if(node.getNodeName().equals("PRODUCAO-BIBLIOGRAFICA")) {
                        NodeList nodePubs  = node.getChildNodes();
                        researcher.articles = getArticles(nodePubs, researcher);

                    }
                }
            }
        }
        researcher
    }

    private Document buildDocument(InputStream file) {
        Document document = null
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        }catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        document
    }

    private Set<Article> getArticles(NodeList nodePubs, Researcher researcher) {
        Set<Article> articles = []
        for (int j = 0; j < nodePubs.getLength(); j++) {
            Node nodePub = nodePubs.item(j);
            if (nodePub instanceof Element){
                if(nodePub.getNodeName().equals("ARTIGOS-PUBLICADOS")) {
                    NodeList articlesNode = nodePub.getChildNodes();
                    for (int k = 0; k < articlesNode.getLength(); k++) {
                        Node articleNode = articlesNode.item(k);
                        NodeList details = articleNode.getChildNodes();
                        Article article = getArticle(details)
                        if(article.validate()){
                            article.owner = researcher
                            articles.add(article)
                        }
                    }
                }
            }
        }
        articles
    }

    private Article getArticle(NodeList details) {
        Article article = new Article();
        for (int l = 0; l < details.getLength(); l++) {
            Node detail = details.item(l);
            if (detail.getNodeName().equals("DADOS-BASICOS-DO-ARTIGO")) {
                article.title = detail.getAttributes().getNamedItem("TITULO-DO-ARTIGO").getNodeValue()
            } else if (detail.getNodeName().equals("DETALHAMENTO-DO-ARTIGO")) {
                article.issn = detail.getAttributes().getNamedItem("ISSN").getNodeValue()
                article.journal = detail.getAttributes().getNamedItem("TITULO-DO-PERIODICO-OU-REVISTA").getNodeValue()
            }
        }
        article
    }
}