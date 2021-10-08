package soccer.game.streetSoccerManager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStats {

    private int id;
    private int overallRating;

    public PlayerStats(int id, int overallRating) {
        this.id = id;
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
