package rpa

class Researcher {
    String name
    String cpf
    static hasMany = [articles:Article, updates:UpdateLattes]
    int citationAmount

    Researcher() {
        articles = []
        updates = []
    }

    static constraints = {
        name(nullable: false, blank: false)
        cpf(unique: true, nullable: false, blank: false, minSize: 11, maxSize: 11)
        citationAmount(blank: true)
    }
}