package pages

import geb.Page
import steps.InternationalizationHelper

class PageWithI18nSupport extends Page {
    InternationalizationHelper helperMsg = InternationalizationHelper.instance
}
