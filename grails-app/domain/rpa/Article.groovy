package rpa

class Article {
    String tittle
    String journal
    String issn
    static hasMany = [authors:Researcher]
    static belongsTo = Researcher
    int citationAmount

    static constraints = {
        tittle(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(unique: true,nullable: false, blank: false)
        citationAmount(blank: true)
    }
}