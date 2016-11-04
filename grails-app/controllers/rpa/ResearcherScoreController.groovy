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

    /*
    *   Receives a ReseacherScore from the .gsp view containing a Reseacher and a Qualis
    *   and scanf throught every Article in the Researcher, counting the score their journal
    *   has in the Qualis. Returns a String for the view to show the final score.
     */
    private String CalculateScore(ResearcherScore researcherScoreInstance){
        Set<Article> researcherArticles = researcherScoreInstance.researcher.articles;
        Set<QualisAvaliation> qualisAvaliations =  researcherScoreInstance.qualis.avaliations;
        Boolean[] avaliated = new Boolean[researcherArticles.size()];
        def categoryPoints = [:];
        for(int i = 0; i < researcherArticles.size(); ++i){
            for(int j = 0; j < qualisAvaliations.size(); ++j){
                if(researcherArticles[i].journal == qualisAvaliations[j].journal){
                    avaliated[i] = true;
                    if(categoryPoints.containsKey(qualisAvaliations[j].avaliation))
                        categoryPoints[qualisAvaliations[j].avaliation]++;
                    else
                        categoryPoints.put(qualisAvaliations[j].avaliation, 1);
                }
            }
        }
        String score = "";
        categoryPoints.each{
            cp -> score += cp.key + ": " + cp.value + "; ";
        }
        int notAvaliated;
        for(int i = 0; i < researcherArticles.size(); ++i){
            if(!avaliated[i]){
                notAvaliated++;
            }
        }
        score += "Not Avaliated: " + notAvaliated + ";";
        score
    }
}
