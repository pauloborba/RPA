package rpa

class Avaliation {

    Qualis qualis
    Researcher researcher
    def categoryPoints = [:]

    static constraints = {
        qualis(nullable: false, blank: false)
        researcher(nullable: false, blank: false)
        categoryPoints(nullable: true, blank: true)
    }



}
