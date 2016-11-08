package rpa

class GrupoPesquisadoresController {
    def index() {redirect(action: "list", params: params) }

    def create() {
        def grupoPesq = new GrupoPesquisadores(params)
        def myList = []
        [grupoPesquisadoresInstance:grupoPesq , PesquisadoresInstanceList: myList]  //  [grupoPesquisadoresInstance: new GrupoPesquisadores(params)]
    }

    def save() {
        def grupoPesquisadoresInstance = new GrupoPesquisadores(params)

        params.list('pesquisador').each{
            grupoPesquisadoresInstance.addPesq(Pesquisador.findByCpf(it))
        }
        grupoPesquisadoresInstance.tamanhoGrupo=grupoPesquisadoresInstance.pesquisadores.size()

        /*if(params.nomeGrupo.equals("Cin UFPE")){
            grupoPesquisadoresInstance.setNota(3,0,"2012")
            grupoPesquisadoresInstance.setNota(4,1,"2013")
            grupoPesquisadoresInstance.setNota(5,2,"2010")
        }
        if(params.nomeGrupo.equals("UFRJ")){
            grupoPesquisadoresInstance.setNota(2,0,"2012")
            grupoPesquisadoresInstance.setNota(4,1,"2013")
            grupoPesquisadoresInstance.setNota(5,2,"2010")
        }*/

        if (!grupoPesquisadoresInstance.save(flush: true)) {
            flash.message = "ERROR"
            render(view: "create")
            return
        }

        redirect(action: "list", params: params)
    }
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [grupoPesquisadoresInstanceList: GrupoPesquisadores.list(params), grupoPesquisadoresInstanceTotal: GrupoPesquisadores.count()]
    }

    def show(Long id) {
        def grupoPesquisadoresInstance = GrupoPesquisadores.get(id)
        if (!grupoPesquisadoresInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rpa.label', default: 'ERROR')])
            redirect(action: "list")
            return
        }

        [grupoPesquisadoresInstance: grupoPesquisadoresInstance]
    }

    def compare(){

    }
    def resultCompare(){
        if(params.grupoSelecionado1.toString().equals(params.grupoSelecionado2.toString())){
            flash.message = message(code: 'default.compareSameGroup.message', default: 'Cannot Compare a Group With Itself')
        }
        else{
            def grupo1 = GrupoPesquisadores.findByNomeGrupo(params.grupoSelecionado1)
            def grupo2 = GrupoPesquisadores.findByNomeGrupo(params.grupoSelecionado2)
            def qualis = params.qualis
            [grupoPesquisadoresInstance1: grupo1, grupoPesquisadoresInstance2: grupo2, qualis: qualis]
        }
    }

}
