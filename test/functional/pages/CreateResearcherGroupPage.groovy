package pages

/**
 * Created by Bruno on 30/11/2016.
 */
class CreateResearcherGroupPage extends PageSupport{
    static url= "grupoPesquisadores/create"
    static at = {
        def createLabel = interMessage.getMessage('default.button.create.label')
        def researcherGroupLabel = interMessage.getMessage('default.ResearcherGroup.label')
        def titleMsg = createLabel+" "+researcherGroupLabel
        title == titleMsg
    }

    def createInfo(String nomeGrupo, String [] nomes){
        $("form").nomeGrupo = nomeGrupo

        def array=[]
        for(int i=0;i<nomes.length;i++)array[i]=nomes[i].toString()
        $("#selectPesq").value(array)

        $("input", type: "submit").click()
    }



}
