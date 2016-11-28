package rpa

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import rpa.GoogleScholarService

@Transactional(readOnly = true)
class ResearcherController {

    static GoogleScholarService gs

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Researcher.list(params), model:[researcherInstanceCount: Researcher.count()]
    }

    def show(Researcher researcherInstance) {
        respond researcherInstance
    }

    def create() {
        respond new Researcher(params)
    }

    def findCitations() {
        Researcher res = Researcher.findByName(params.researcher)
        Article art = Article.findByTitle(params.article)
        List<Article> lista = new ArrayList<Article>()
        lista.add(art)
        gs = new GoogleScholarService()
        gs.findCitations(lista)
        render(view: "citations", model: [citationsCount: lista.get(0).citationAmount, researcher: params.researcher, article: params.article])
    }

    def citations() {
        render(view: "citations", model: [citationsCount: "", researcher: "", article: ""])
    }

    @Transactional
    def save(Researcher researcherInstance) {
        if (researcherInstance == null) {
            notFound()
            return
        }

        if (researcherInstance.hasErrors()) {
            respond researcherInstance.errors, view:'create'
            return
        }

        researcherInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'researcher.label', default: 'Researcher'), researcherInstance.id])
                redirect researcherInstance
            }
            '*' { respond researcherInstance, [status: CREATED] }
        }
    }

    def edit(Researcher researcherInstance) {
        respond researcherInstance
    }

    @Transactional
    def update(Researcher researcherInstance) {
        if (researcherInstance == null) {
            notFound()
            return
        }

        if (researcherInstance.hasErrors()) {
            respond researcherInstance.errors, view:'edit'
            return
        }

        researcherInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Researcher.label', default: 'Researcher'), researcherInstance.id])
                redirect researcherInstance
            }
            '*'{ respond researcherInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Researcher researcherInstance) {

        if (researcherInstance == null) {
            notFound()
            return
        }

        researcherInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Researcher.label', default: 'Researcher'), researcherInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcher.label', default: 'Researcher'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
