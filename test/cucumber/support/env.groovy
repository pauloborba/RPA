package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
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
    scenarioInterceptor.destroy()

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