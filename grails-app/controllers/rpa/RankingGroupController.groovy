package rpa

import rpa.GroupNotFoundException
import rpa.ResearchGroup
import rpa.Researcher
import rpa.Article
import rpa.QualisAvaliation


class RankingGroupController {


    def index(){
        [groups: ResearchGroup.find()]
    }

    def show(String groupName){
        def group = ResearchGroup.find({name: groupName})

        def ranking = []
        def error = ""

        try{
            ranking = groupRanking(group)
        } catch(Exception e){
            error = e.message()
        }

        [ rank:ranking, error: error]
    }

    Integer gradeResearcher (Researcher pq){
        def grade = 0
        Article[] articles = pq.articles
        for (article in articles){

            // grade == A1 + A2 + B1
            QualisAvaliation[] avaliations = QualisAvaliation.find{ it.issn == article.issn }

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

        return ranking
    }
}

class Rank {
    String name
    Integer grade
}

