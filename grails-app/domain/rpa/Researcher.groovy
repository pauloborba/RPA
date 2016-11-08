package rpa

class Researcher {
    String name
    String cpf
    static hasMany = [articles:Article]

    static constraints = {
        name(nullable: false, blank: false)
        cpf(unique: true, nullable: false, blank: false, minSize: 11, maxSize: 11)
    }
}
