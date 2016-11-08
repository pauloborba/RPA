package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResearchGroupController {

    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def createGroup(String name) {
        def grupo = new ResearchGroup(name: name)
        grupo.properties = params
        grupo.save()
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResearchGroup.list(params), model:[researchGroupInstanceCount: ResearchGroup.count()]
    }

    def show(ResearchGroup researchGroupInstance) {
        respond researchGroupInstance
    }

    def create() {
        respond new ResearchGroup(params)
    }

    @Transactional
    def save() {
        def grupoPesquisadoresInstance = new ResearchGroup(params)
/*
        params.list('pesquisador').each{
            grupoPesquisadoresInstance.addPesq(Pesquisador.findByCpf(it))
        }
        grupoPesquisadoresInstance.tamanhoGrupo=grupoPesquisadoresInstance.pesquisadores.size()*/

        if (!grupoPesquisadoresInstance.save(flush: true)) {
            flash.message = "ERROR"
            render(view: "create")
            return
        }

        redirect(action: "list", params: params)
    }

    def edit(ResearchGroup researchGroupInstance) {
        respond researchGroupInstance
    }

    @Transactional
    def update(ResearchGroup researchGroupInstance) {
        if (researchGroupInstance == null) {
            notFound()
            return
        }

        if (researchGroupInstance.hasErrors()) {
            respond researchGroupInstance.errors, view:'edit'
            return
        }

        researchGroupInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ResearchGroup.label', default: 'ResearchGroup'), researchGroupInstance.id])
                redirect researchGroupInstance
            }
            '*'{ respond researchGroupInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ResearchGroup researchGroupInstance) {

        if (researchGroupInstance == null) {
            notFound()
            return
        }

        researchGroupInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ResearchGroup.label', default: 'ResearchGroup'), researchGroupInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'researchGroup.label', default: 'ResearchGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
