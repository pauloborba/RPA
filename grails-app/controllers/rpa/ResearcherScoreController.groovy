package rpa



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResearcherScoreController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResearcherScore.list(params), model:[researcherScoreInstanceCount: ResearcherScore.count()]
    }

    def show(ResearcherScore researcherScoreInstance) {
        respond researcherScoreInstance
    }

    def create() {
        respond new ResearcherScore(params)
    }

    @Transactional
    def save(ResearcherScore researcherScoreInstance) {
        if (researcherScoreInstance == null) {
            notFound()
            return
        }

        if (researcherScoreInstance.hasErrors()) {
            respond researcherScoreInstance.errors, view:'create'
            return
        }

        researcherScoreInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'researcherScore.label', default: 'ResearcherScore'), researcherScoreInstance.id])
                redirect researcherScoreInstance
                researcherScoreInstance.score = CalculateScore(researcherScoreInstance);
                researcherScoreInstance.articlesNotFound = NotAvaliated(researcherScoreInstance.researcher.articles, researcherScoreInstance.qualis.avaliations)
            }
            '*' { respond researcherScoreInstance, [status: CREATED] }

        }
    }

    def edit(ResearcherScore researcherScoreInstance) {
        respond researcherScoreInstance
    }

    @Transactional
    def update(ResearcherScore researcherScoreInstance) {
        if (researcherScoreInstance == null) {
            notFound()
            return
        }

        if (researcherScoreInstance.hasErrors()) {
            respond researcherScoreInstance.errors, view:'edit'
            return
        }


        researcherScoreInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ResearcherScore.label', default: 'ResearcherScore'), researcherScoreInstance.id])
                redirect researcherScoreInstance
                researcherScoreInstance.score = CalculateScore(researcherScoreInstance)
                researcherScoreInstance.articlesNotFound = NotAvaliated(researcherScoreInstance.researcher.articles, researcherScoreInstance.qualis.avaliations)
            }
            '*'{ respond researcherScoreInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ResearcherScore researcherScoreInstance) {

        if (researcherScoreInstance == null) {
            notFound()
            return
        }

        researcherScoreInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ResearcherScore.label', default: 'ResearcherScore'), researcherScoreInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcherScore.label', default: 'ResearcherScore'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def PointsPerCategory(def articles, def avaliations){
        def points = [:]
        for(int i = 0; i < articles.size(); ++i){
            for(int j = 0; j < avaliations.size(); ++j){
                if(articles[i].journal == avaliations[j].journal){
                    if(points.containsKey(avaliations[j].avaliation))
                        points[avaliations[j].avaliation]++
                    else
                        points.put(avaliations[j].avaliation, 1)
                }
            }
        }
        points
    }

    def CalculateScore(ResearcherScore researcherScoreInstance){
        def researcherArticles = researcherScoreInstance.researcher.articles
        def qualisAvaliations =  researcherScoreInstance.qualis.avaliations
        def categoryPoints = PointsPerCategory(researcherArticles, qualisAvaliations)
        def score = ""
        categoryPoints.each{
            cp -> score += cp.key + ": " + cp.value + "; "
        }
        def notAvaliated = NotAvaliated(researcherArticles, qualisAvaliations)
        score += "Not Avaliated: " + notAvaliated.size() + ";"
        score
    }

    def NotAvaliated(def articles, def avaliations){
        def avaliated = new Boolean[articles.size()]
        def notAvaliated = new HashSet<Article>()
        for(int i = 0; i < articles.size(); ++i){
            for(int j = 0; j < avaliations.size(); ++j){
                if(articles[i].journal == avaliations[j].journal){
                    avaliated[i] = true
                }
            }
        }
        for(int i = 0; i < articles.size(); ++i) {
            if (!avaliated[i]) {
                notAvaliated.add(articles[i])
            }
        }
        notAvaliated
    }
}
