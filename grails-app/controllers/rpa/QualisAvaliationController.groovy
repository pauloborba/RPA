package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QualisAvaliationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QualisEvaluation.list(params), model:[qualisAvaliationInstanceCount: QualisEvaluation.count()]
    }

    def show(QualisEvaluation qualisAvaliationInstance) {
        respond qualisAvaliationInstance
    }

    def create() {
        respond new QualisEvaluation(params)
    }

    @Transactional
    def save(QualisEvaluation qualisAvaliationInstance) {
        if (qualisAvaliationInstance == null) {
            notFound()
            return
        }

        if (qualisAvaliationInstance.hasErrors()) {
            respond qualisAvaliationInstance.errors, view:'create'
            return
        }

        qualisAvaliationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'qualisAvaliation.label', default: 'QualisEvaluation'), qualisAvaliationInstance.id])
                redirect qualisAvaliationInstance
            }
            '*' { respond qualisAvaliationInstance, [status: CREATED] }
        }
    }

    def edit(QualisEvaluation qualisAvaliationInstance) {
        respond qualisAvaliationInstance
    }

    @Transactional
    def update(QualisEvaluation qualisAvaliationInstance) {
        if (qualisAvaliationInstance == null) {
            notFound()
            return
        }

        if (qualisAvaliationInstance.hasErrors()) {
            respond qualisAvaliationInstance.errors, view:'edit'
            return
        }

        qualisAvaliationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'QualisEvaluation.label', default: 'QualisEvaluation'), qualisAvaliationInstance.id])
                redirect qualisAvaliationInstance
            }
            '*'{ respond qualisAvaliationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(QualisEvaluation qualisAvaliationInstance) {

        if (qualisAvaliationInstance == null) {
            notFound()
            return
        }

        qualisAvaliationInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'QualisEvaluation.label', default: 'QualisEvaluation'), qualisAvaliationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'qualisAvaliation.label', default: 'QualisEvaluation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
