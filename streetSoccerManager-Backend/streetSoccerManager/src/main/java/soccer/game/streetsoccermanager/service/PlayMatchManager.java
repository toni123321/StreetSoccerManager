package soccer.game.streetsoccermanager.service;

import soccer.game.streetsoccermanager.model.entities.FriendlyMatch;
import soccer.game.streetsoccermanager.model.entities.Match;
import soccer.game.streetsoccermanager.model.entities.Team;

import java.util.Arrays;
import java.util.Random;


public class PlayMatchManager {

    private PlayMatchManager(){
    }

    public static Match playFriendlyMatch(Match match, String command, Boolean isUserTeamHome) {

        FriendlyMatch friendlyMatch;
        String result = match.getResult();
        String statistic = match.getStatistic();
        int currentMin = match.getCurrentMinute();

        int action = getActionNr(match.getCurrentMinute());

        if(isCommandValid(command, action, isUserTeamHome) && action <= 15) {
            result = updateMatchResult(match, command, isUserTeamHome);
            currentMin += 2;
        }

        if(action == 15){
            statistic = "Match ended";
        }
        friendlyMatch = new FriendlyMatch(match.getId(), match.getHomeTeam(), match.getAwayTeam(), result, statistic, currentMin);
        return friendlyMatch;

    }

    private static String updateMatchResult(Match match, String command, Boolean isUserTeamHome){
        // Goals
        int homeTeamGoals = Integer.parseInt(match.getResult().substring(0, 1));
        int awayTeamGoals = Integer.parseInt(match.getResult().substring(2));

        // Teams
        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();
        boolean homeTeamAttacks = false;
        int goals = 0;

        if((command.equals("ATTACK") && isUserTeamHome) ||
                ((command.equals("DEF") || command.equals("OPPONENT")) && !isUserTeamHome)){
            homeTeamAttacks = true;
        }

        if(homeTeamAttacks) {
            goals = playAction(homeTeam, awayTeam);
            homeTeamGoals += goals;
        }
        else{
            goals = playAction(awayTeam, homeTeam);
            awayTeamGoals += goals;
        }
        return homeTeamGoals + ":" + awayTeamGoals;
    }

    private static int playAction(Team attackTeam, Team defTeam){
        int attacking = 0;
        int defence = 0;
        attacking =
                (RatingManager.
                        calcStartingPlayersRatingOnPosCategory(attackTeam, "MID") +
                        RatingManager.
                                calcStartingPlayersRatingOnPosCategory(attackTeam, "ATACK")
                ) / 2;

        defence =
                (RatingManager.
                        calcStartingPlayersRatingOnPosCategory(defTeam, "MID") +
                        RatingManager.
                                calcStartingPlayersRatingOnPosCategory(defTeam, "DEF") +
                        RatingManager.
                                calcStartingPlayersRatingOnPosCategory(defTeam, "GK")
                ) / 3;
        return scoreGoal(attacking, defence);
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


    private static int getActionNr(int currMinute){
        Integer[] minutesIntervals = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28};
        if(Arrays.asList(minutesIntervals).contains(currMinute)){
            return Arrays.asList(minutesIntervals).indexOf(currMinute) + 1;
        }
        return 0;
    }

    private static boolean isCommandValid(String command, int action, Boolean isUserTeamHome) {
        String [] acceptedUserCommands = {"ATTACK", "DEF"};
        String opponentCommand = "OPPONENT";
        Boolean commandValid;

        if(action % 2 != 0){
            if(Boolean.TRUE.equals(isUserTeamHome)) {
                commandValid = Arrays.stream(acceptedUserCommands).anyMatch(command::contains);
            }
            else{
                commandValid = opponentCommand.equals(command);
            }
        }
        else {
            if(Boolean.TRUE.equals(isUserTeamHome)) {
                commandValid = opponentCommand.equals(command);
            }
            else{
                commandValid = Arrays.stream(acceptedUserCommands).anyMatch(command::contains);
            }
        }
        return commandValid;
    }


}
