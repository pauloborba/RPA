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
        try {
            Node mainNode = getDocumentMainNode(file);
            if (mainNode.getNodeName().equals("CURRICULO-VITAE")){
                NodeList nodeList = mainNode.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    getDadosGerais(node, researcher);
                    getProducaoBibliografica(node, researcher);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            researcher = null;
        }
        researcher
    }

    private Node getDocumentMainNode(InputStream file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file).getDocumentElement();;
    }

    private void getDadosGerais(Node node, Researcher researcher) {
        if (node instanceof Element && node.getNodeName().equals("DADOS-GERAIS")) {
            researcher.name = node.getAttributes().getNamedItem("NOME-COMPLETO").getNodeValue();
            researcher.cpf = node.getAttributes().getNamedItem("CPF").getNodeValue();
        }
    }

    private void getProducaoBibliografica(Node node, Researcher researcher) {
        if (node instanceof Element && node.getNodeName().equals("PRODUCAO-BIBLIOGRAFICA")) {
            NodeList nodePubs = node.getChildNodes();
            researcher.articles = getArticles(nodePubs);
        }
    }

    private Set<Article> getArticles(NodeList nodePubs) {
        Set<Article> articles = []
        for (int j = 0; j < nodePubs.getLength(); j++) {
            Node nodePub = nodePubs.item(j);
            if (nodePub instanceof Element && nodePub.getNodeName().equals("ARTIGOS-PUBLICADOS")) {
                NodeList articlesNode = nodePub.getChildNodes();
                addArticles(articlesNode, articles);
            }
        }
        articles
    }

    private void addArticles(NodeList articlesNode, Set<Article> articles) {
        for (int k = 0; k < articlesNode.getLength(); k++) {
            Node articleNode = articlesNode.item(k);
            NodeList details = articleNode.getChildNodes();
            Article article = getArticleDetails(details);
            if(article.validate()){
                articles.add(article)
            }
        }
    }

    private Article getArticleDetails(NodeList details) {
        Article article = new Article();
        for (int l = 0; l < details.getLength(); l++) {
            Node detail = details.item(l);
            if (detail.getNodeName().equals("DADOS-BASICOS-DO-ARTIGO")) {
                article.tittle = detail.getAttributes().getNamedItem("TITULO-DO-ARTIGO").getNodeValue()
            } else if (detail.getNodeName().equals("DETALHAMENTO-DO-ARTIGO")){
                article.issn = detail.getAttributes().getNamedItem("ISSN").getNodeValue()
                article.journal = detail.getAttributes().getNamedItem("TITULO-DO-PERIODICO-OU-REVISTA").getNodeValue()
            }
        }
        article
    }
}