package support

import rpa.Researcher
import rpa.ResearchGroup
import rpa.Article

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {

}

After() {

    Researcher.list().each {
        it.delete(flush:true)
    }
    ResearchGroup.list().each {
        it.delete(flush:true)
    }
    Article.list().each {
        it.delete(flush:true)
    }

    bindingUpdater.remove()
}