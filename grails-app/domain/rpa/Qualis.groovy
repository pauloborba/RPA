package rpa

class Qualis {
    String description
    static hasMany = [avaliations:QualisEvaluation]

    static constraints = {
        description(nullable: false, blank: false)
    }

}
