package rpa

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='attribute,typeUpdate')
class UpdateLattes {
    String attribute
    UpdateType typeUpdate
    static belongsTo = [owner:Researcher]
    public UpdateLattes(String attribute, UpdateType typeUpdate, Researcher owner){
        this.attribute = attribute
        this.typeUpdate = typeUpdate
        this.owner = owner
    }
    static constraints = {
        attribute(blank: false, nullable: false)
    }
}
