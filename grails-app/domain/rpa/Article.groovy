package rpa

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='title,journal,issn,authors')
class Article {
    String title
    String journal
    String issn
    Set<Author> authors
    static hasMany = [authors:Author]
    static belongsTo = [owner:Researcher]

    Article(){
        authors = []
    }

    static constraints = {
        title(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(nullable: false, blank: false)
        owner(nullable: true)
    }
}