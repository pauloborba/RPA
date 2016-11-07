package rpa

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='tittle,journal,issn')
class Article {
    String tittle
    String journal
    String issn
    static belongsTo = [owner:Researcher]

    static constraints = {
        tittle(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(nullable: false, blank: false)
        owner(nullable: true)
    }
}
