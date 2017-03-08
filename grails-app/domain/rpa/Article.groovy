package rpa

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='title,journal,issn,authors')
class Article {
    String title
    String journal
    String issn
    //Adicionado para que o EqualsAndHashCode reconhecesse o hasMany e pudesse comparar
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
