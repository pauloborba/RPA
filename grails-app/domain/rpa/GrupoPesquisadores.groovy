package rpa
/**
 * Created by Bruno on 22/10/2016.
 */
class GrupoPesquisadores {
    String nomeGrupo
    static hasMany = [pesquisadores:Pesquisador]
    int tamanhoGrupo

    GrupoPesquisadores(){
        pesquisadores = [];
        tamanhoGrupo=0

    }

    public Pesquisador findPesqByCPF(String cpf){
        if (this.pesquisadores != null) {
            for(int i =0; i<this.pesquisadores.size(); i++){
                if(this.pesquisadores[i].getCpf().equals(cpf)){
                    return this.pesquisadores[i];
                }
            }
            return null
        }
        else return null
    }

    public void addPesq(Pesquisador p){
        if(this.findPesqByCPF(p.getCpf())==null) {
            pesquisadores.add(p)
        }
    }

    public void addMember(int n){
        for(int i=0;i<n;i++)pesquisadores.add(new Pesquisador())
    }

    //stub method
    public int getGroupScore(String criterio, String qualis, String nomeGrupo){
        if(nomeGrupo.equals("Cin UFPE")){
            if(qualis.equals("2010")){
                if(criterio.equals("A1"))return 2
                else if(criterio.equals("A2"))return 4
                else if(criterio.equals("B1"))return 5
                else if(criterio.equals("B2"))return 8
                else if(criterio.equals("B3"))return 3
                else return 0
            }
            else if(qualis.toString().equals("2012")){
                if(criterio.equals("A1"))return 3
                else if(criterio.equals("A2"))return 2
                else if(criterio.equals("B1"))return 5
                else if(criterio.equals("B2"))return 6
                else if(criterio.equals("B3"))return 8
                else return 0
            }
            else if(qualis.toString().equals("2013")){
                if(criterio.equals("A1"))return 2
                else if(criterio.equals("A2"))return 4
                else if(criterio.equals("B1"))return 5
                else if(criterio.equals("B2"))return 0
                else if(criterio.equals("B3"))return 1
                else return 0
            }
            else return 0
        }
        else if(nomeGrupo.equals("UFRJ")){
            if(qualis.equals("2010")){
                if(criterio.equals("A1"))return 3
                else if(criterio.equals("A2"))return 5
                else if(criterio.equals("B1"))return 5
                else if(criterio.equals("B2"))return 0
                else if(criterio.equals("B3"))return 8
                else return 0
            }
            else if(qualis.equals("2012")){
                if(criterio.equals("A1"))return 2
                else if(criterio.equals("A2"))return 3
                else if(criterio.equals("B1"))return 4
                else if(criterio.equals("B2"))return 5
                else if(criterio.equals("B3"))return 7
                else return 0
            }
            else if(qualis.equals("2013")){
                if(criterio.equals("A1"))return 3
                else if(criterio.equals("A2"))return 4
                else if(criterio.equals("B1"))return 1
                else if(criterio.equals("B2"))return 0
                else if(criterio.equals("B3"))return 3
                else return 0
            }
            else return 0
        }
    }

    static constraints = {
        nomeGrupo nullable: false, unique: true
    }

}
