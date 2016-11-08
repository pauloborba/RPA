package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AvaliationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Avaliation.list(params), model:[avaliationInstanceCount: Avaliation.count()]
    }

    def show(Avaliation avaliationInstance) {
        respond avaliationInstance
    }

    def create() {
        respond new Avaliation(params)
    }

    @Transactional
    def save(Avaliation avaliationInstance) {
        if (avaliationInstance == null) {
            notFound()
            return
        }

        if (avaliationInstance.hasErrors()) {
            respond avaliationInstance.errors, view:'create'
            return
        }

        avaliationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'avaliation.label', default: 'Avaliation'), avaliationInstance.id])
                redirect avaliationInstance
                avaliationInstance.categoryPoints = CalculateScore(avaliationInstance)
            }
            '*' { respond avaliationInstance, [status: CREATED] }
        }
    }

    def edit(Avaliation avaliationInstance) {
        respond avaliationInstance
    }

    @Transactional
    def update(Avaliation avaliationInstance) {
        if (avaliationInstance == null) {
            notFound()
            return
        }

        if (avaliationInstance.hasErrors()) {
            respond avaliationInstance.errors, view:'edit'
            return
        }

        avaliationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Avaliation.label', default: 'Avaliation'), avaliationInstance.id])
                redirect avaliationInstance
            }
            '*'{ respond avaliationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Avaliation avaliationInstance) {

        if (avaliationInstance == null) {
            notFound()
            return
        }

        avaliationInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Avaliation.label', default: 'Avaliation'), avaliationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'avaliation.label', default: 'Avaliation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    String CalculateScore(Avaliation avaliation){
        Set<Article> researcherArticles = avaliation.researcher.articles
        Set<QualisAvaliation> qualisAvaliations = avaliation.qualis.avaliations
        def categoryPoints = [:]
        for(int i = 0; i < researcherArticles.size(); ++i){
            for(int j = 0; j < qualisAvaliations.size(); ++j){
                if(researcherArticles[i].journal == qualisAvaliations[j].journal){
                    if(categoryPoints.containsKey(qualisAvaliations[j].avaliation))
                        categoryPoints[qualisAvaliations[j].avaliation]++
                    else
                        categoryPoints.put(qualisAvaliations[j].avaliation, 1)
                }
            }
        }
        String score = ""
        categoryPoints.each{
            cp -> score += cp.key + ": " + cp.value + "; "
        }
        score
    }


}
