package pages

import geb.Page

class VisualizationAvaliationPage extends Page{

    static url = "/RPA/avaliation/show"

    static at = {
        title ==~ /Show Avaliation/
    }

    boolean Visualization(){
        $("a", text: "B3: 1; ")
    }

}
