package rpa

class ResearcherController {

    def index() {redirect(action: "list", params: params) }

    def create() {
        [ResearcherInstance: new Researcher(params)]
    }

    def save() {
        def ResearcherInstance = new Researcher(params)
        ResearcherInstance.save()
        redirect(action: "create")
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ResearcherInstanceList: Researcher.list(params), ResearcherInstanceTotal: Researcher.count()]
    }

    def show(Long id) {
        def ResearcherInstance = Researcher.get(id)
        if (!ResearcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rpa.label', default: 'Pesquisadores'), nome])
            redirect(action: "list")
            return
        }

        [ResearcherInstance: ResearcherInstance]
    }

    def remove() {
        if (params.ResearchersSelector == null && params.typed == ""){
            flash.message = message(code: 'nothingSelected', default: 'Select at least one researcher to remove')
            redirect(action: "removeStep1")
        }

        else if(params.ResearchersSelector != null && params.typed != ""){
            flash.message = message(code: 'bothUsed', default: 'You can not use the text field and the checklist at the same time')
            redirect(action: "removeStep1")
        }
        else if (params.ResearchersSelector != null && params.typed == "") {
            def ResearcherInstanceList = []
            params.list('ResearchersSelector').each{
                def ResearcherInstance = Researcher.findByCpf(it)
                if (ResearcherInstance != null) ResearcherInstanceList.add(ResearcherInstance)
            }
            ResearcherInstanceList.each{
                if (it.delete(flush: true)) {
                    params.remove(it)
                }
            }
            redirect(action: "list")
        }

        else {
            def ResearcherInstance = Researcher.findByCpf(params.typed)
            if (ResearcherInstance != null) {
                ResearcherInstance.delete(flush: true)
                redirect(action: "list")
            }
            else {
                flash.message = message(code: 'NotExist', default: 'CPF not valid! You have to select a existing researcher!')
                redirect(action: "removeStep1")
            }
        }

    }

    def removeStep1(){


    }

}
