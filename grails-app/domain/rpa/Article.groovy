package rpa

class Article {
    String tittle
    String journal
    String issn

    static constraints = {
        tittle(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        issn(nullable: false, blank: false)
    }

    Article(String title, String journal, String issn){
        this.tittle = title
        this.journal = journal
        this.issn = issn
    }
}
