package rpa
/**
 * Created by Bruno on 22/10/2016.
 */
class Artigo {
    //issn é um identificador do artigo
    //para ver como o artigo foi avaliado segundo algum qualis, entre em:
    //https://sucupira.capes.gov.br/sucupira/public/consultas/coleta/veiculoPublicacaoQualis/listaConsultaGeralPeriodicos.jsf
    //e coloque o issn,qualis e o nomeCurso no local indicado
    String issn
    String titArtigo
    String nomeCurso
    static hasMany = [pesquisadores:Pesquisador]
    static belongsTo = [Pesquisador]

    def getClassificacao(String qualis){
        //obter a classificacao do artigo, segundo o qualis passado como parametro
        //o issn e o nomeCurso serão usados para buscar esse artigo
    }


    static constraints = {
        issn nullable: false
        titArtigo nullable: false
        nomeCurso nullable: false
    }
}
