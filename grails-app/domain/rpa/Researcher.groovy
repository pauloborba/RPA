package rpa

class Researcher {
    String name
    String cpf
    static hasMany = [articles:Article, diffs:Diff]

    def beforeUpdate() {
        //Esse callback nao estava funcionando para os articles
        if(isDirty('name')){
            Diff diff = new Diff()
            diff.attributeNew = this.name
            diff.attributeOld = this.getPersistentValue('name')
            diff.typeDiff = 3
            this.addToDiffs(diff)
        }
        return true
    }

    Researcher(){
        articles = []
        diffs = []
    }

    static constraints = {
        name(nullable: false, blank: false)
        cpf(unique: true, nullable: false, blank: false, minSize: 11, maxSize: 11)
    }

    def update(Researcher newResearcher){
        //Removendo validacao do cpf
        def allFields = Researcher.class.declaredFields
                .collectMany{!it.synthetic ? [it.name] : []}
        def excludedFields = ['cpf']
        def allButExcluded = allFields - excludedFields

        if(!newResearcher.validate(allButExcluded)){
            return
        }

        this.name = newResearcher.name
        addNewArticle(newResearcher)
        removeUnusedArticle(newResearcher)
        this.save(flush: true)
    }

    private void removeUnusedArticle(Researcher newResearcher) {
        def articlesToRemove = []
        for (oldArticle in this.articles) {
            boolean isSame = false
            for (nArticle in newResearcher.articles) {
                if (oldArticle.equals(nArticle)) {
                    isSame = true
                    break
                }
            }
            if (!isSame) {
                this.addToDiffs(new Diff(oldArticle.tittle, null,2 ,this))
                articlesToRemove << oldArticle
            }
        }
        articlesToRemove.each {
            this.removeFromArticles(it)
            it.delete(flush: true)
        }
    }

    private void addNewArticle(Researcher newResearcher) {
        for (nArticle in newResearcher.articles) {
            boolean isSame = false
            for (oldArticle in this.articles) {
                if (nArticle.equals(oldArticle)) {
                    isSame = true
                    break
                }
            }
            if (!isSame) {
                this.addToArticles(nArticle)
                this.addToDiffs(new Diff(nArticle.tittle, null, 1, this))
            }
        }
    }
}
