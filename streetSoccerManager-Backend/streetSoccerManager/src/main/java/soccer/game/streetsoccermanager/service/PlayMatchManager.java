package soccer.game.streetsoccermanager.service;

import soccer.game.streetsoccermanager.model.entities.FriendlyMatch;
import soccer.game.streetsoccermanager.model.entities.Match;

public class PlayMatchManager {

    public static Match playFriendlyMatch(Match match, String command) {
        int homeTeamRating = RatingManager.calcTeamOverallRating(match.getHomeTeam());
        int awayTeamRating = RatingManager.calcTeamOverallRating(match.getAwayTeam());

        FriendlyMatch friendlyMatch;
        String result = "";
        String statistic = "";

        if(homeTeamRating > awayTeamRating){
            result = "1:0";
            statistic = "Home team win";

        }
        else if(homeTeamRating < awayTeamRating){
            result = "0:1";
            statistic = "Away team win";
        }
        else
        {
            result = "0:0";
            statistic = "Draw";
        }
        friendlyMatch = new FriendlyMatch(match.getId(), match.getHomeTeam(), match.getAwayTeam(), result, statistic, 0);
        return friendlyMatch;
    }


}
