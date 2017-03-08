package rpa

class ResearchGroup {
    String name
    static hasMany = [researchers:Researcher]

    static constraints = {
        name(nullable: false, unique: true, validator: {!it.matches(".*[^\\w\\s].*")})
    }

    def findResearcher(Researcher researcher) {
        return this.researchers.contains(researcher)
    }

    def groupSizeEqualsTo(int size) {
        return this.researchers.size() == size
    }
}
