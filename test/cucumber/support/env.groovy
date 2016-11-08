package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import rpa.Qualis
import rpa.QualisAvaliation

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()

    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor(appCtx)
    scenarioInterceptor.init()
}

After() {
    Qualis.list().each {
        QualisAvaliation.where{qualis == it}.deleteAll()
        it.delete(flush: true)
    }

    scenarioInterceptor.destroy()

    bindingUpdater.remove()
}