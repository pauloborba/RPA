package rpa

class ResearcherScore {
    Researcher researcher
    Qualis qualis
    String score

    static constraints = {
        researcher(nullable: false, blank: false)
        qualis(nullable: false, blank: false)
        score(nullable: true, blank: true)
    }
}
