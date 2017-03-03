package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ArticleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Article.list(params), model:[articleInstanceCount: Article.count()]
    }

    def show(Article articleInstance) {
        respond articleInstance
    }

    def create() {
        respond new Article(params)
    }

    @Transactional
    def save(Article articleInstance) {
        if (articleInstance == null) {
            notFound()
            return
        }

        if (articleInstance.hasErrors()) {
            respond articleInstance.errors, view:'create'
            return
        }

        articleInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'article.label', default: 'Article'), articleInstance.id])
                redirect articleInstance
            }
            '*' { respond articleInstance, [status: CREATED] }
        }
    }

    def edit(Article articleInstance) {
        respond articleInstance
    }

    @Transactional
    def update(Article articleInstance) {
        if (articleInstance == null) {
            notFound()
            return
        }

        if (articleInstance.hasErrors()) {
            respond articleInstance.errors, view:'edit'
            return
        }

        articleInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Article.label', default: 'Article'), articleInstance.id])
                redirect articleInstance
            }
            '*'{ respond articleInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Article articleInstance) {

        if (articleInstance == null) {
            notFound()
            return
        }

        articleInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Article.label', default: 'Article'), articleInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label', default: 'Article'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
