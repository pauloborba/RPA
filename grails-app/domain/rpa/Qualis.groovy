package rpa

class Qualis {
    String year
    static hasMany = [avaliations:QualisAvaliation]

    static constraints = {
        year(nullable: false, blank: false)
    }
}