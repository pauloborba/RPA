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
    }

    public void addPesq(Pesquisador p){
        if(this.findPesqByCPF(p.getCpf())==null) {
            pesquisadores.add(p)
        }
    }

    //stub method
    public int getNotaStub(int tipo,String qualis,String nomeGrupo){
        if(nomeGrupo.equals("Cin UFPE")){
            if(qualis.equals("2010")){
                if(convertQualis(tipo).equals("B1"))return 5
                else return 0
            }
            else if(qualis.toString().equals("2012")){
                if(convertQualis(tipo).equals("A1"))return 3
                else return 0
            }
            else if(qualis.toString().equals("2013")){
                if(convertQualis(tipo).equals("A2"))return 4
                else return 0
            }
            else return 0
        }
        else if(nomeGrupo.equals("UFRJ")){
            if(qualis.equals("2010")){
                if(convertQualis(tipo).equals("B1"))return 5
                else return 0
            }
            else if(qualis.equals("2012")){
                if(convertQualis(tipo).equals("A1"))return 2
                else return 0
            }
            else if(qualis.equals("2013")){
                if(convertQualis(tipo).equals("A2"))return 4
                else return 0
            }
            else return 0
        }
    }


    public String convertQualis(int i){
        if(i==0){
            return "A1"
        }
        else if(i==1){
            return "A2"
        }
        else if(i==2){
            return "B1"
        }
        else if(i==3){
            return "B2"
        }
        else if(i==4){
            return "B3"
        }
        else if(i==5){
            return "B4"
        }
        else if(i==6){
            return "B5"
        }
        else if(i==7){
            return "C "
        }
    }

    public int revertQualis(String criterio){
        if(criterio.equals("A1")){
            return 0
        }
        else if(criterio.equals("A2")){
            return 1
        }
        else if(criterio.equals("B1")){
            return 2
        }
        else if(criterio.equals("B2")){
            return 3
        }
        else if(criterio.equals("B3")){
            return 4
        }
        else if(criterio.equals("B4")){
            return 5
        }
        else if(criterio.equals("B5")){
            return 6
        }
        else if(criterio.equals("C")){
            return 7
        }
    }

    static constraints = {
        nomeGrupo nullable: false, unique: true
    }

}
