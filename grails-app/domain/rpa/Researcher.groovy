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
        def lastUpdates = []
        if(!newResearcher.validate(validatesWithoutCpf())){
            return lastUpdates
        }
        lastUpdates += addNewArticleFromUpdate(newResearcher)
        lastUpdates += removeArticleFromUpdate(newResearcher)
        this.save(flush: true)
        lastUpdates
    }

    //Removendo validacao do cpf
    private List validatesWithoutCpf() {
        def allFields = Researcher.class.declaredFields
                .collectMany { !it.synthetic ? [it.name] : [] }
        def excludedFields = ['cpf']
        def allButExcluded = allFields - excludedFields
        allButExcluded
    }
    //Guardo as atualizações e retorno os novas atualizacoes de artigos que foram removidos
    private def removeArticleFromUpdate(Researcher newResearcher) {
        def updates = []
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
                UpdateLattes update = new UpdateLattes(oldArticle.title,UpdateType.REMOVE_ARTICLE ,this)
                this.addToUpdates(update)
                updates << update
                articlesToRemove << oldArticle
            }
        }
        articlesToRemove.each {
            this.removeFromArticles(it)
            it.delete(flush: true)
        }
        updates
    }
    //Guardo as atualizações e retorno os novas atualizacoes de artigos que foram adicionados
    private def addNewArticleFromUpdate(Researcher newResearcher) {
        def updates = []
        for (nArticle in newResearcher.articles) {
            boolean isSame = false
            for (oldArticle in this.articles) {
                if (nArticle.equals(oldArticle)) {
                    isSame = true
                    break
                }
            }
            if (!isSame) {
                UpdateLattes update = new UpdateLattes(nArticle.title, UpdateType.ADD_ARTICLE, this)
                this.addToArticles(nArticle)
                this.addToUpdates(update)
                updates << update
            }
        }
        updates
    }
}
