package rpa

class QualisEvaluation {
    String journal
    String avaliation

    static constraints = {
        journal(nullable: false, blank: false)
        avaliation(nullable: false, blank: false)
    }

}
