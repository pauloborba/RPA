package rpa

class Qualis {
    String year
    static hasMany = [avaliations:QualisEvaluation]

    static constraints = {
        year(nullable: false, blank: false)
    }

    Qualis(String year, Set<QualisEvaluation> avaliations){
        this.year = year
        this.avaliations = avaliations
    }
}
