package support

import rpa.Researcher
import rpa.ResearchGroup
import rpa.Article

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()

    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor(appCtx)
    scenarioInterceptor.init()
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
    scenarioInterceptor.destroy()

    bindingUpdater.remove()
}