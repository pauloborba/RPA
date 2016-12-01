package rpa

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResearcherController {

    static GoogleScholar gs

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
        Researcher researcher = Researcher.findByName(params.researcher)
        List<Article> lista = new ArrayList<Article>()
        def name
        def citationNumber
        if (researcher == null) {
            flash.message = 'Researcher not found'
            name = params.researcher
            citationNumber = "Researcher not found"
        } else {
            researcher.articles.each { article ->
                lista.add(article)
            }
            gs = new GoogleScholar()
            def totalCitations = gs.findCitations(lista)
            gs.updateCitations(researcher, totalCitations)
            name = researcher.name
            citationNumber = totalCitations
        }
        chain(controller: 'researcher', action: 'researcherCitations', model: [citationsCount: citationNumber, researcher: name])
    }

    def researcherCitations() {
        if (chainModel != null) {
            render(view: "findCitations", model: [citationsCount: chainModel['citationsCount'] ?: "", researcher: chainModel['researcher'] ?: ""])
        } else {
            render(view: "findCitations", model: [citationsCount: "", researcher: ""])
        }
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
