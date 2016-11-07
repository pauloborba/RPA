package rpa

import rpa.GroupNotFoundException
import rpa.ResearchGroup
import rpa.Researcher

class RankingGroupController {


    def groupRanking (ResearchGroup group) throws GroupNotFoundException {

        if(group == null){
            throw new GroupNotFoundException()
        }

        def ranking = []
        // generate ranking
        for (int i = 0; i < group.researchers.size(); i++) {
            //ranking = ranking + { name: group[i].name; grade: 0 }

        }

        ranking = ranking.sort { it.grade }

        return ranking
    }

    def renderRanking (String groupName){
        def group = ResearcherGroup.findByName(groupName)

        def ranking = []

        try{
            ranking = groupRanking(group)
        } catch(Exception e){
            e.printStackTrace()
        }

        render(view: "rankingGroup", model: [ranking: ranking])
    }
}