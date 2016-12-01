package rpa

/**
 * Created by Bruno on 21/10/2016.
 */
class Pesquisador {
    //informaçoes relevantes para cadastrar o pesquisador:
    //nome,cpf e publicacoes
    String nome;
    String cpf;
    static hasMany = [listaPublicacoes:Artigo]

    Pesquisador(){
        listaPublicacoes = [];
    }

    static constraints = {
        nome nullable: false, blank: false
        cpf nullable: false, unique: true, size: 11..11
    }

    static mapping = {
        sort nome: "asc"
    }
}
