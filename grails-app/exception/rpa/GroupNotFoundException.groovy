package rpa

class GroupNotFoundException extends Exception {
    public GroupNotFoundException(){
        super("Grupo nao foi encontrado!")
    }

}