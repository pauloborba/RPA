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

    def importFile(){
        def xml = request.getFile('file')
        if(!xml.empty){
            XmlExtractorService xmlExtractor = new XmlExtractorService()
            Researcher researcherSaved = saveOrUpdateResearcher(xmlExtractor, xml)
            if (researcherSaved.validate()) {
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

    private Researcher saveOrUpdateResearcher(XmlExtractorService xmlExtractor, xml) {
        def researcherFromXml = xmlExtractor.getResearcher(xml.getInputStream())
        def researcherSaved = Researcher.findByCpf(researcherFromXml.cpf)

        if (researcherSaved != null) {
            researcherSaved.update(researcherFromXml)
        } else {
            researcherSaved = researcherFromXml
            researcherSaved.save()
        }
        researcherSaved
    }
}
