package pages

/**
 * Created by Bruno on 30/11/2016.
 */
class CreateResearcherPage extends PageSupport{
    static url = "pesquisador/create"
    static at = {
        def createLabel = interMessage.getMessage('default.button.create.label')
        def researcherLabel = interMessage.getMessage('default.researcher.label')
        def titleMsg = createLabel+" "+researcherLabel
        title == titleMsg
    }

    def dataToAdd(String nome, String cpf){
        $("form").nome = nome
        $("form").cpf = cpf
        $("input", type: "submit").click()
    }
}
