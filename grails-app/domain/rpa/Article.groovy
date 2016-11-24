package rpa

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='title,journal,issn')
class Article {
    String title
    String journal
    String issn
    static belongsTo = [owner:Researcher]

    static constraints = {
        title(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(nullable: false, blank: false)
        owner(nullable: true)
    }
}
