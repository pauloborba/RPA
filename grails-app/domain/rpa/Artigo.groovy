package rpa
/**
 * Created by Bruno on 22/10/2016.
 */
class Artigo {
    //issn é um identificador do artigo
    //para ver como o artigo foi avaliado segundo algum qualis, entre em:
    //https://sucupira.capes.gov.br/sucupira/public/consultas/coleta/veiculoPublicacaoQualis/listaConsultaGeralPeriodicos.jsf
    //e coloque o issn,qualis no local indicado
    String issn
    String titArtigo
    static hasMany = [pesquisadores:Pesquisador]
    static belongsTo = [Pesquisador]

    static constraints = {
        issn nullable: false
        titArtigo nullable: false
    }
}
