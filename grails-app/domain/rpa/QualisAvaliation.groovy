package rpa

class QualisAvaliation {
    String issn
    String journal
    String evaluation
    String subject

    static belongsTo = [qualis:Qualis]

    static constraints = {
        issn(nullable: false, blank: false)
        journal(nullable: false, blank: false)
        evaluation(nullable: false, blank: false)
        subject(nullable: false, blank: false)
    }
}