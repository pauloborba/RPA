package rpa

import rpa.Researcher
import rpa.Article

class ResearcherController {

    def create(){

    }

    def index(){

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

    def update(){
        Researcher researcherSaved = params['researcherSaved']
        Researcher researcherNew = params['researcherNew']
        researcherSaved.save flush:true
    }

    def importFile(){
        def xml = request.getFile('file')
        if(!xml.empty){
            XmlExtractorService xmlExtractor = new XmlExtractorService()
            def researcherXml = xmlExtractor.getResearcher(xml.getInputStream())
            def researcherSaved = Researcher.findByCpf(researcherXml.cpf)

            if (researcherSaved != null) {
                params << [researcherNew: researcherXml, researcherSaved: researcherSaved]
                researcherSaved = update()
            } else {
                params << [name: researcherXml.name, cpf: researcherXml.cpf, articles: researcherXml.articles]
                researcherSaved = save()
            }

            if (researcherSaved) {
                flash.message = message(code: 'researcher.saved')
                redirect action: 'show', id: researcherSaved.id
            }else{
                flash.message = message(code: 'researcher.file.invalid')
                redirect action: 'create'
            }
        }else{
            flash.message = message(code: 'researcher.file.empty')
            render(view: "create")
        }
    }
}