package rpa

class ResearcherController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [researcherInstanceList: Researcher.list(params), researcherInstanceTotal: Researcher.count()]
    }

    def show = {
        def researcherInstance = researcher.get(params.id)
        if (!researcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcher.label', default: 'Researcher'), params.id])
            redirect(action: "index")
            return
        }

        [researcherInstance: researcherInstance]
    }

    def create = {
        def researcher = new Researcher(params)
        [researcherInstance: researcher]
    }

    def save = {
        def researcherInstance = new Researcher(params)
        if (!researcherInstance.save(flush: true)) {
            render(view: "create", model: [researcherInstance: researcherInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'researcher.label', default: 'Researcher'), researcherInstance.id])
        redirect(action: "show", id: researcherInstance.id)
    }
}
