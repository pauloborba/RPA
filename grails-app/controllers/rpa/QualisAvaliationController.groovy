package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QualisAvaliationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QualisAvaliation.list(params), model:[qualisAvaliationInstanceCount: QualisAvaliation.count()]
    }

    def show(QualisAvaliation qualisAvaliationInstance) {
        respond qualisAvaliationInstance
    }

    def create() {
        respond new QualisAvaliation(params)
    }

    @Transactional
    def save(QualisAvaliation qualisAvaliationInstance) {
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
                flash.message = message(code: 'default.created.message', args: [message(code: 'qualisAvaliation.label', default: 'QualisAvaliation'), qualisAvaliationInstance.id])
                redirect qualisAvaliationInstance
            }
            '*' { respond qualisAvaliationInstance, [status: CREATED] }
        }
    }

    def edit(QualisAvaliation qualisAvaliationInstance) {
        respond qualisAvaliationInstance
    }

    @Transactional
    def update(QualisAvaliation qualisAvaliationInstance) {
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'QualisAvaliation.label', default: 'QualisAvaliation'), qualisAvaliationInstance.id])
                redirect qualisAvaliationInstance
            }
            '*'{ respond qualisAvaliationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(QualisAvaliation qualisAvaliationInstance) {

        if (qualisAvaliationInstance == null) {
            notFound()
            return
        }

        qualisAvaliationInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'QualisAvaliation.label', default: 'QualisAvaliation'), qualisAvaliationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'qualisAvaliation.label', default: 'QualisAvaliation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
