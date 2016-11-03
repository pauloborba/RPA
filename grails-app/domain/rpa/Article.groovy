package rpa

class Article {
    String tittle
    String journal
    String issn
    static hasMany = [authors:Researcher]
    static belongsTo = Researcher

    static constraints = {
        tittle(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(unique: true,nullable: false, blank: false)
    }
}
