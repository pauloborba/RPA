package rpa

import rpa.exception.GroupNotFoundException

class RankingGroupController {

    def groupName

    def index(){
        def groups = ResearchGroup.findAll()
        groups = [new ResearchGroup([name:"arthur", researchers:[]])]
        def groupNames = []
        for(it in groups){
            groupNames.add(it.name)
        }
        groupName = ""
        [groups: groupNames]
    }

    def showGroup() {
        groupName = name
        redirect(action:'show', params:[groupName: groupName])
    }

    def show(String groupName){
        def group = ResearchGroup.find({name: groupName})

        def ranking = []
        def error = ""

        try{
            ranking = groupRanking(group)
        } catch(Exception e){
            error = e.message
        }

        [ rank:ranking, error: error]
    }

    Integer gradeResearcher (Researcher pq){
        def grade = 0
        Article[] articles = pq.articles
        for (article in articles){

            // grade == A1 + A2 + B1
            QualisAvaliation[] avaliations = QualisAvaliation.find{ issn == article.issn }

            for (avaliation in avaliations){
                if(avaliation == "A1" || avaliation == "A2" || avaliation == "B1")
                    grade++
            }
        }

        return grade
    }

    Rank[] groupRanking (ResearchGroup group) throws GroupNotFoundException {

        if(group == null){
            throw new GroupNotFoundException()
        }

        Rank[] ranking = []
        // generate ranking, rank ==
        for (pq in group.researchers) {

            def grade = gradeResearcher(pq)

            ranking << new Rank(name: pq.name, grade: grade)
        }

        ranking.sort{it.rank}
        ranking = ranking.reverse()

        ranking = [new Rank(name:"arthur", grade:10)]

        return ranking
    }
}

class Rank {
    String name
    Integer grade
}

