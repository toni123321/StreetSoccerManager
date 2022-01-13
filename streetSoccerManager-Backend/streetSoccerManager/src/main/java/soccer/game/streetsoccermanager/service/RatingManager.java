package soccer.game.streetsoccermanager.service;
import soccer.game.streetsoccermanager.model.entities.PlayerStats;
import soccer.game.streetsoccermanager.model.entities.PlayerTeamInfo;
import soccer.game.streetsoccermanager.model.entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RatingManager {
    private RatingManager() {

    }

    public static int calcPlayerOverallRating(PlayerStats playerStats){
        return (playerStats.getSkills() + playerStats.getPhysical()) / 2;
    }


    public static int calcTeamOverallRating(Team team){
        int overallRating = 0;
        int startingPlayersRating = calcStartingPlayersRating(team);
        int reservesRating = calcReservesPlayersRating(team);

        overallRating = ((int) Math.round((startingPlayersRating * 0.8) + (reservesRating * 0.2)));
        return overallRating;
    }

    private static List<PlayerTeamInfo> getStartingPlayersTeamInfo(Team team) {
        try {
            return team.getPlayersTeamInfo().stream().filter(p ->
                            p.getPlayer().getPlayerPositionInfo().isStarting()).
                    collect(Collectors.toList());
        }
        catch (NullPointerException e){
            return new ArrayList<>();
        }
    }

    private static List<PlayerTeamInfo> getStartingPlayersTeamInfoOnPosCategory(Team team, String positionCategory){
        try {
            return team.getPlayersTeamInfo().stream().filter(p ->
                            p.getPlayer().getPlayerPositionInfo().isStarting() &&
                            p.getPlayer().getPlayerPositionInfo().getCurrentPosition().getCategory().equals(positionCategory)).
                    collect(Collectors.toList());
        }
        catch (NullPointerException e){
            return new ArrayList<>();
        }
    }

    private static List<PlayerTeamInfo> getReservesPlayersTeamInfo(Team team) {
        try {
            return team.getPlayersTeamInfo().stream().filter(p ->
                            !p.getPlayer().getPlayerPositionInfo().isStarting()).
                    collect(Collectors.toList());
        }
        catch (NullPointerException e){
            return new ArrayList<>();
        }
    }

    public static int calcStartingPlayersRating(Team team){
        int startingPlayersRating = 0;

        for (PlayerTeamInfo playerTeamInfo: getStartingPlayersTeamInfo(team)) {
            startingPlayersRating += calcPlayerOverallRating(playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats());
        }
        if(Boolean.FALSE.equals(getStartingPlayersTeamInfo(team).isEmpty())) {
            startingPlayersRating /= getStartingPlayersTeamInfo(team).size();
        }
        return startingPlayersRating;
    }

    public static int calcStartingPlayersRatingOnPosCategory(Team team, String positionCategory){
        int playersRating = 0;

        for (PlayerTeamInfo playerTeamInfo: getStartingPlayersTeamInfoOnPosCategory(team, positionCategory)) {
            playersRating += calcPlayerOverallRating(playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats());
        }
        if(Boolean.FALSE.equals(getStartingPlayersTeamInfoOnPosCategory(team, positionCategory).isEmpty())) {
            playersRating /= getStartingPlayersTeamInfoOnPosCategory(team, positionCategory).size();
        }
        return playersRating;
    }

    public static int calcReservesPlayersRating(Team team) {
        int reservesRating = 0;
        for (PlayerTeamInfo playerTeamInfo: getReservesPlayersTeamInfo(team)) {
            reservesRating += calcPlayerOverallRating(playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats());
        }
        if(Boolean.FALSE.equals(getReservesPlayersTeamInfo(team).isEmpty())) {
            reservesRating /= getReservesPlayersTeamInfo(team).size();
        }
        return reservesRating;

    }

}
