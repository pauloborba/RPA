package steps
import rpa.*
class Auxiliar {

    static grupos = [
            [nomeGrupo: "Cin UFPE"
            ],
            [nomeGrupo: "UFRJ",
            ]]


    static public def findByNomeGrupo(String nome) {
        grupos.find {  grupo->
           if(grupo.nomeGrupo == nome)return grupo
        }
    }
    static public void createPesquisador(String nome, cpf) {
        def cont = new PesquisadorController()
        cont.params << [nome: nome,cpf: cpf]
        cont.create()
        cont.save()
        cont.response.reset()
    }

    static public void createGrupo(String nome) {
        def cont = new GrupoPesquisadoresController()
        cont.params << [nomeGrupo: nome]
        cont.request.setContent(new byte[1000])
        cont.create()
        cont.save()
        cont.response.reset()
    }

    static public void setNota(GrupoPesquisadores grupo, int nota,int tipo, String qualis){
        //System.out.println("Entrou")
        //def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
        if(grupo==null)println("AQUI")
        else println("AQUI22")
        if(qualis.equals("2010") ){
           grupo.medias2010[tipo]= nota
        }
        else if(qualis.equals("2011")){
            grupo.medias2011[tipo]= nota
        }
        else if(qualis.equals("2012")){
            grupo.medias2012[tipo]= nota
        }
        else if(qualis.equals("2013")){
            grupo.medias2013[tipo]= nota
        }
        else if(qualis.equals("2014")){
            grupo.medias2014[tipo]= nota
        }
        else if(qualis.equals("2015")){
            grupo.medias2015[tipo]= nota
        }
    }

    static public int getNota(String nome, int tipo, String qualis){
        def grupo = GrupoPesquisadores.findByNomeGrupo(nome)
        if(qualis.equals("2010") ){
            return grupo.medias2010[tipo]
        }
        else if(qualis.equals("2011")){
            return grupo.medias2011[tipo]
        }
        else if(qualis.equals("2012")){
            return grupo.medias2012[tipo]
        }
        else if(qualis.equals("2013")){
            return grupo.medias2013[tipo]
        }
        else if(qualis.equals("2014")){
            return grupo.medias2014[tipo]
        }
        else if(qualis.equals("2015")){
            return grupo.medias2015[tipo]
        }
    }

    static public int selectCriterio(String tipo){
        int n
        switch (tipo) {
            case "A1":
                return n = 0
                break
            case "A2":
                return n = 1
                break
            case "B1":
                return n = 2
                break
            case "B2":
                return n = 3
                break
            case "B3":
                return n = 4
                break
            case "B4":
                return n = 5
                break
            case "B5":
                return n = 6
                break
            case "C":
                return n = 7
                break
        }
    }
}