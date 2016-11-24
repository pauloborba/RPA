package pages

import geb.Page
import grails.plugin.remotecontrol.*

class PageWithI18nSupport extends Page {
    RemoteControl remote = new RemoteControl()

    String getMessage(String code, Object[] args = null, Locale locale = Locale.getDefault()) {
        if (args != null) {
            args = args as Object[]
        }
        remote.exec { ctx.messageSource.getMessage(code, args, locale) }
    }

}
