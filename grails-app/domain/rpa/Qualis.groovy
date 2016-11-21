package rpa

class Qualis {
    String description
    static hasMany = [avaliations:QualisEvaluation]

    static constraints = {
        description(nullable: false, blank: false)
    }

    Qualis(String description, Set<QualisEvaluation> avaliations){
        this.description = description
        this.avaliations = avaliations
    }
}
