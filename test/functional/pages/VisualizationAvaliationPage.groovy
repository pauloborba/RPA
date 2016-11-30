package pages

import geb.Page

class VisualizationAvaliationPage extends InternacionalizedPage{

    static url = "/RPA/avaliation/show"

    static at = {
        title ==~ helperMsg.getMessage('default.show.label', 'Avaliation')
    }

    boolean Visualization(){
        $("a", text: "B3: 1; ")
    }

}
