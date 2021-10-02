package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStats {

    private int overallRating;

    public PlayerStats(int overallRating) {
        this.overallRating = overallRating;
    }

    //    private int physical;
//    private int defence;
//    private int shooting;
//    private int goalkeeping;
//
//    private int pace;
//    private int passing;
//    private int dribbling;
//    private int defending;



}
