package rpa

class ResearchGroup {
    String name
    static hasMany = [researchers:Researcher]

    static constraints = {
        name(nullable: false, unique: true)
    }
}