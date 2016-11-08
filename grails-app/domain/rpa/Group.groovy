package rpa

class Group {
    String name
    static hasMany = [researchers:Researcher]

    static constraints = {
        name(unique:true, nullable:false, blank:false)
    }
}
