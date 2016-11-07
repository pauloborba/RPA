package rpa

class Diff {
    String attributeOld
    String attributeNew
    //1-adicionado, 2-removido, 3-update name
    int typeDiff
    static belongsTo = [owner:Researcher]
    public Diff(String attributeOld, String attributeNew, int typeDiff, Researcher owner){
        this.attributeOld = attributeOld
        this.attributeNew = attributeNew
        this.typeDiff = typeDiff
        this.owner = owner
    }
    static constraints = {
        attributeOld(blank: false, nullable: false)
        attributeNew(nullable: true)
        typeDiff(min: 1, max: 3)
    }
}
