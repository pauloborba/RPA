package pages

import geb.Page

/**
 * Created by rbb3 on 03/11/16.
 */
class Citation extends Page {
    static url = "reseacher/citation"

    static at = {
        title ==~ /Citation/
    }

    static def isDialogOpen() {
        $(".ui-dialog").is(":visible")
    }
}
