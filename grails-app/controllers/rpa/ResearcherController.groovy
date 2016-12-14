package rpa

class ResearcherController {

    def create() {

    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Researcher.list(params), model:[researcherInstanceCount: Researcher.count()]
    }

    def show(){
        def researcherInstance = Researcher.findById(params.id)
        if (!researcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcher.label', default: 'Pesquisador'), params.id])
            return
        }
        [researcherInstance: researcherInstance]
    }

    def save(){
        Researcher r = new Researcher(params)
        r.articles.each {
            r.addToArticles(it)
        }
        r.save()
    }

    def importFile(){
        def xml = request.getFile('file')
        if(!xml.empty){
            XmlExtractorService xmlExtractor = new XmlExtractorService()
            def researcherXml = xmlExtractor.getResearcher(xml.getInputStream())
            if (researcherXml == null) {
                flash.message = message(code: 'researcher.file.invalid')
                redirect action: 'create'
            } else {
                params << [name: researcherXml.name, cpf: researcherXml.cpf, articles: researcherXml.articles]
                researcherXml = save()
                flash.message = message(code: 'researcher.saved')
                redirect action: 'show', id: researcherXml.id
            }
        }else{
            flash.message = message(code: 'researcher.file.empty')
            redirect action: 'create'
        }
    }
}