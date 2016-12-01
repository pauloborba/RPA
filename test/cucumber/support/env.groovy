package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import rpa.Artigo
import rpa.GrupoPesquisadores
import rpa.GrupoPesquisadoresController
import rpa.Pesquisador

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()

    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor(appCtx)
    scenarioInterceptor.init()
}

After() {
    GrupoPesquisadores.list().each {it.delete(flush: true)}
    Pesquisador.list().each {it.delete(flush:true)}
    Artigo.list().each {it.delete(flush: true)}
    scenarioInterceptor.destroy()
    bindingUpdater.remove()
}