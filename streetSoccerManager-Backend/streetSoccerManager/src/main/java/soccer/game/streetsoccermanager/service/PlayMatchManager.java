package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import soccer.game.streetsoccermanager.model.entities.CustomTeam;
import soccer.game.streetsoccermanager.model.entities.FriendlyMatch;
import soccer.game.streetsoccermanager.model.entities.Match;
import soccer.game.streetsoccermanager.model.entities.Team;
import soccer.game.streetsoccermanager.service_interfaces.ITeamService;

import java.util.Arrays;
import java.util.Random;


public class PlayMatchManager {

    private PlayMatchManager(){
    }

    public static Match playFriendlyMatch(Match match, String command, Boolean isUserTeamHome) {
        int homeTeamRating = RatingManager.calcTeamOverallRating(match.getHomeTeam());
        int awayTeamRating = RatingManager.calcTeamOverallRating(match.getAwayTeam());

        FriendlyMatch friendlyMatch;
        String result = "";
        String statistic = "";
        int currentMin = match.getCurrentMinute();

        int action = getActionNr(match.getCurrentMinute());
//        Boolean isUserTeamHome = isUserTeamHome(match);

        if(isCommandValid(match, command, action, isUserTeamHome)) {
            if(action <= 15){
                result = updateMatchResult(match, command, isUserTeamHome);
                currentMin += 2;
            }
        }

        friendlyMatch = new FriendlyMatch(match.getId(), match.getHomeTeam(), match.getAwayTeam(), result, statistic, currentMin);
        return friendlyMatch;

    }

    private static String updateMatchResult(Match match, String command, Boolean isUserTeamHome){
        int homeTeamGoals = Integer.parseInt(match.getResult().substring(0, 1));
        int awayTeamGoals = Integer.parseInt(match.getResult().substring(2));

//        Boolean isUserTeamHome = isUserTeamHome(match);

        String result = match.getResult();

        int ratingATTACKHomeTeam = 0;
        int ratingMIDHomeTeam = 0;
        int ratingDEFHomeTeam = 0;

        int ratingATTACKAwayTeam = 0;
        int ratingMIDAwayTeam = 0;
        int ratingDEFAwayTeam = 0;

        if((command.equals("ATTACK") && isUserTeamHome) ||
                ((command.equals("DEF") || command.equals("OPPONENT")) && !isUserTeamHome)){
            ratingMIDHomeTeam = RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getHomeTeam(), "MID");
            ratingATTACKHomeTeam = RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getHomeTeam(), "ATACK");

            ratingMIDAwayTeam = RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getAwayTeam(), "MID");
            ratingDEFAwayTeam = (RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getAwayTeam(), "DEF") +
                    RatingManager.calcStartingPlayersRatingOnPosCategory(match.getAwayTeam(), "GK")) / 2;

            int attacking = (ratingATTACKHomeTeam + ratingMIDHomeTeam) / 2;
            int defence = (ratingMIDAwayTeam + ratingDEFAwayTeam) / 2;

            // call function score_goal()
            int goals = scoreGoal(attacking, defence);
            homeTeamGoals += goals;
        }
        else {
            ratingMIDAwayTeam = RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getAwayTeam(), "MID");
            ratingATTACKAwayTeam = RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getAwayTeam(), "ATACK");

            ratingMIDHomeTeam = RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getHomeTeam(), "MID");
            ratingDEFHomeTeam = (RatingManager.
                    calcStartingPlayersRatingOnPosCategory(match.getHomeTeam(), "DEF") +
                    RatingManager.calcStartingPlayersRatingOnPosCategory(match.getHomeTeam(), "GK")) / 2;

            int attacking = (ratingATTACKAwayTeam + ratingMIDAwayTeam) / 2;
            int defence = (ratingMIDHomeTeam + ratingDEFHomeTeam) / 2;

            // call function score_goal()
            int goals = scoreGoal(attacking, defence);
            awayTeamGoals += goals;
        }
        result = homeTeamGoals + ":" + awayTeamGoals;
        return result;
    }

    private static int scoreGoal(int attackRating, int defRating) {
        int[] goalChance75 = {0, 1, 1, 1};
        int[] goalChance50 = {0, 1};
        int[] goalChance25 = {0, 0, 0, 1};
        int pos = 0;
        int goals = 0;

        if (attackRating > defRating) {
            // chance 75
            pos = new Random().nextInt(goalChance75.length);
            goals = goalChance75[pos];
        }
        else if (attackRating < defRating) {
            // chance 25
            pos = new Random().nextInt(goalChance25.length);
            goals = goalChance25[pos];
        }
        else{
            // chance 50
            pos = new Random().nextInt(goalChance50.length);
            goals = goalChance50[pos];
        }
        return goals;
    }
    private static Boolean isUserTeamHome(Match match){
        if(match.getHomeTeam() instanceof CustomTeam){
            return true;
        }
        return false;
    }

    private static int getActionNr(int currMinute){
        Integer[] minutesIntervals = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28};
        if(Arrays.asList(minutesIntervals).contains(((Integer) currMinute))){
            return Arrays.asList(minutesIntervals).indexOf(currMinute) + 1;
        }
        return 0;
    }

    private static boolean isCommandValid(Match match, String command, int action, Boolean isUserTeamHome) {
        String [] acceptedUserCommands = {"ATTACK", "DEF"};
        String opponentCommand = "OPPONENT";
        Boolean commandValid = false;

//        Boolean isUserTeamHome = isUserTeamHome(match);
        if(action % 2 != 0){
            if(isUserTeamHome) {
                commandValid = Arrays.stream(acceptedUserCommands).anyMatch(command::contains);
            }
            else{
                commandValid = opponentCommand.equals(command);
            }
        }
        else {
            if(isUserTeamHome) {
                commandValid = opponentCommand.equals(command);
            }
            else{
                commandValid = Arrays.stream(acceptedUserCommands).anyMatch(command::contains);
            }
        }
        return commandValid;
    }


}
