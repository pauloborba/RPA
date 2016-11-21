package rpa

class Article {
    String title
    String journal
    String issn
    static belongsTo = Researcher

    static constraints = {
        title(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(nullable: false, blank: false)
    }
}