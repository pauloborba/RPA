package rpa

class Article {
    String tittle
    String journal
    String issn
    static belongsTo = Researcher

    static constraints = {
        tittle(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(nullable: false, blank: false)
    }
}