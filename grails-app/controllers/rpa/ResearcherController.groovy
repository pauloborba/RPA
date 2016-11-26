package rpa

import rpa.Researcher
import rpa.Article

class ResearcherController {
    private def  lastUpdates = []
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

        def diff
        if(chainModel != null) {
            diff = chainModel['diff']
        }else{
            diff = []
        }
        render(view: "show", model: [researcherInstance: researcherInstance, lastUpdates: diff])
    }

    def importFile(){
        lastUpdates = []
        def xml = request.getFile('file')
        if(!xml.empty){
            XmlExtractorService xmlExtractor = new XmlExtractorService()
            Researcher researcherSaved = saveOrUpdateResearcher(xmlExtractor, xml)
            if (researcherSaved.validate()) {
                if(lastUpdates.size() > 0){
                    flash.message = message(code: 'researcher.updated')
                }else{
                    flash.message = message(code: 'researcher.saved')
                }
                chain (action: 'show', id: researcherSaved.id, model: [diff: lastUpdates])
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
        if(researcherFromXml == null){
            researcherFromXml = new Researcher()
        }
        def researcherSaved = Researcher.findByCpf(researcherFromXml?.cpf)

        if (researcherSaved != null) {
            lastUpdates = researcherSaved.update(researcherFromXml)
        } else {
            researcherSaved = researcherFromXml
            researcherSaved.save()
        }
        researcherSaved
    }
}
