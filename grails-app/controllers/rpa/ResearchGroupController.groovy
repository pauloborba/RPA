package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResearchGroupController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

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
    def save(ResearchGroup researchGroupInstance) {
        if (researchGroupInstance == null || researchGroupInstance.name.matches(".*[^\\w\\s].*")) {
            notFound()
            return
        }//Da true para qualquer string que contenha ao menos um char não alfanúmérico nem espaço.

        if (researchGroupInstance.hasErrors()) {
            respond researchGroupInstance.errors, view:'create'
            return
        }

        researchGroupInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'researchGroup.label', default: 'ResearchGroup'), researchGroupInstance.id])
                redirect researchGroupInstance
            }
            '*' { respond researchGroupInstance, [status: CREATED] }
        }
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
