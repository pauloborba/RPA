package rpa

class ResearcherScore {
    Researcher researcher
    Qualis qualis
    String score
    static hasMany = [articlesNotFound: Article]

    static constraints = {
        researcher(nullable: false, blank: false)
        qualis(nullable: false, blank: false)
        score(nullable: true, blank: true)
        articlesNotFound(nullable: true, blank: true)
    }

}
