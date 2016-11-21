package rpa


class Qualis {
    String title

    static hasMany = [qualisAvaliations: QualisAvaliation]

    static constraints = {
        title(nullable: false, blank: false, unique: true)
    }
}
