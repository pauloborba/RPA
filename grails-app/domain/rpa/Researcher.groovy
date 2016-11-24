package rpa

class Researcher {
    String name
    String cpf
    static hasMany = [articles:Article, updates:UpdateLattes]

    Researcher(){
        articles = []
        updates = []
    }

    static constraints = {
        name(nullable: false, blank: false)
        cpf(unique: true, nullable: false, blank: false, minSize: 11, maxSize: 11)
    }

    def update(Researcher newResearcher){
        if(!newResearcher.validate(validatesWithoutCpf())){
            return null
        }

        addNewArticleFromUpdate(newResearcher)
        removeArticleFromUpdate(newResearcher)
        this.save(flush: true)
    }

    //Removendo validacao do cpf
    private List validatesWithoutCpf() {
        def allFields = Researcher.class.declaredFields
                .collectMany { !it.synthetic ? [it.name] : [] }
        def excludedFields = ['cpf']
        def allButExcluded = allFields - excludedFields
        allButExcluded
    }

    private void removeArticleFromUpdate(Researcher newResearcher) {
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
                this.addToUpdates(new UpdateLattes(oldArticle.title,UpdateType.REMOVE_ARTICLE ,this))
                articlesToRemove << oldArticle
            }
        }
        articlesToRemove.each {
            this.removeFromArticles(it)
            it.delete(flush: true)
        }
    }

    private void addNewArticleFromUpdate(Researcher newResearcher) {
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
                this.addToUpdates(new UpdateLattes(nArticle.title, UpdateType.ADD_ARTICLE, this))
            }
        }
    }
}
