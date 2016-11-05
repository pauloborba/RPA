package rpa

class Qualis {
    String year
    static hasMany = [avaliations:QualisAvaliation]

    static constraints = {
        year(nullable: false, blank: false)
    }

    Qualis(String year, Set<QualisAvaliation> avaliations){
        this.year = year
        this.avaliations = avaliations
    }
}
