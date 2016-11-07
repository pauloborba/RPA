package steps

import rpa.Researcher
import rpa.ResearcherController

/**
 * Created by rbb3 on 06/11/16.
 */
class ResearcherSteps {

    static researchers = [
            [
                    name: "Paulo Borba",
                    cpf: "11111111111"
            ],
            [
                    name: "Joao da Silva",
                    cpf: "22222222222"
            ]
    ]

    static public def findResearcher(String name) {
        researchers.find { researcher ->
            researcher.name == name
        }
    }

    static public void createResearcher(String name, String cpf) {
        def cont = new ResearcherController()
        cont.params << findResearcher(name) << [name: name]

        if (cpf != null) {
            cont.params["cpf"] = cpf
        }

        cont.request.setContent(new Byte[1000])
        cont.create()
        cont.save()
        cont.response.reset()
    }

    static public boolean isResearcherStored(researcher, name) {
        def sResearcher = Researcher.findByName(name)
        def hasResearcher = true
        if (sResearcher != null && researcher != null) {
            sResearcher.each { key, data ->
                hasResearcher = researcher."$key" == data
            }
            return hasResearcher
        } else {
            return true
        }
    }

}
