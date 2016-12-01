package rpa

class PesquisadorController {

    def index() {redirect(action: "list", params: params) }

    def create() {
        [PesquisadorInstance: new Pesquisador(params)]//  [grupoPesquisadoresInstance: new GrupoPesquisadores(params)]
    }

    def save() {
        def PesquisadorInstance = new Pesquisador(params)
        if (!PesquisadorInstance.save(flush: true)) {
            flash.message = "ERROR"
            render(view: "create")
            return
        }
        redirect(action: "create")
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [PesquisadorInstanceList: Pesquisador.list(params), PesquisadorInstanceTotal: Pesquisador.count()]
    }

    def show(Long id) {
        def PesquisadorInstance = Pesquisador.get(id)
        if (!PesquisadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rpa.label', default: 'Pesquisadores'), nome])
            redirect(action: "list")
            return
        }

        [PesquisadorInstance: PesquisadorInstance]
    }



}
