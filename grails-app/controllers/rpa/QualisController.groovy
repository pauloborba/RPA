package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QualisController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Qualis.list(params), model:[qualisInstanceCount: Qualis.count()]
    }

    def show(Qualis qualisInstance) {
        respond qualisInstance
    }

    def create() {
        respond new Qualis(params)
    }

    @Transactional
    def save(Qualis qualisInstance) {
        if (qualisInstance == null) {
            notFound()
            return
        }

        if (qualisInstance.hasErrors()) {
            respond qualisInstance.errors, view:'create'
            return
        }

        qualisInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'qualis.label', default: 'Qualis'), qualisInstance.id])
                redirect qualisInstance
            }
            '*' { respond qualisInstance, [status: CREATED] }
        }
    }

    def edit(Qualis qualisInstance) {
        respond qualisInstance
    }

    @Transactional
    def update(Qualis qualisInstance) {
        if (qualisInstance == null) {
            notFound()
            return
        }

        if (qualisInstance.hasErrors()) {
            respond qualisInstance.errors, view:'edit'
            return
        }

        qualisInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Qualis.label', default: 'Qualis'), qualisInstance.id])
                redirect qualisInstance
            }
            '*'{ respond qualisInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Qualis qualisInstance) {

        if (qualisInstance == null) {
            notFound()
            return
        }

        qualisInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Qualis.label', default: 'Qualis'), qualisInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'qualis.label', default: 'Qualis'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
