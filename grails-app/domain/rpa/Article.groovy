package rpa

class Article {
    String title
    String journal
    String issn
    Set<Author> authors
    static hasMany = [authors:Author]
    static belongsTo = [owner:Researcher]
    int citationAmount

    Article() {
        authors = []
    }

    static constraints = {
        title(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(nullable: false, blank: false)
        owner(nullable: true)
        citationAmount(blank: true)
    }
}