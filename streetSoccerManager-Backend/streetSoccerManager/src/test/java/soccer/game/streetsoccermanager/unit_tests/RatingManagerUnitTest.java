package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.service.RatingManager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
class RatingManagerUnitTest {

    CustomTeam customTeam;
    OfficialTeam officialTeam;

    @BeforeEach
    public void setUp()  {
        customTeam = new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER"));
        new OfficialTeam(2l, "Barcelona", new Formation(2l, "2-1-1"), "Ronald Koeman");

        List<PlayerTeamInfo> playersTeamInfo = List.of(
                new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1")))
        );
        List<Player> players = List.of(
                new Player(1l,
                        new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                        new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                        new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80))),
                new Player(2l,
                        new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), false),
                        new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))
        );

        playersTeamInfo.get(0).setPlayer(players.get(0));
        playersTeamInfo.get(1).setPlayer(players.get(1));
        customTeam.setPlayersTeamInfo(new HashSet<>(playersTeamInfo));

    }

    @Test
    void calcPlayerOverallRating() {
        PlayerStats playerStats = new PlayerStats(1l, 80, 80);
        int ovrRating = (playerStats.getSkills() + playerStats.getPhysical())/2;
        Assertions.assertEquals(ovrRating, RatingManager.calcPlayerOverallRating(playerStats));
    }

    @Test
    void calcStartingPlayersRating(){
        int ovrRating = RatingManager.calcPlayerOverallRating(new ArrayList<>(customTeam.getPlayersTeamInfo()).get(0).
                getPlayer().getPlayerAdditionalInfo().getPlayerStats());
        Assertions.assertEquals(ovrRating, RatingManager.calcStartingPlayersRating(customTeam));
        Assertions.assertEquals(0, RatingManager.calcStartingPlayersRating(officialTeam));
    }

    @Test
    void calcStartingPlayersRatingOnPosCategory(){
        int ovrRating = RatingManager.calcPlayerOverallRating(new ArrayList<>(customTeam.getPlayersTeamInfo()).get(0).
                getPlayer().getPlayerAdditionalInfo().getPlayerStats());
        Assertions.assertEquals(ovrRating, RatingManager.calcStartingPlayersRatingOnPosCategory(customTeam, "ATACK"));
    }


    @Test
    void calcReservesPlayersRating(){
        int ovrRating = RatingManager.calcPlayerOverallRating(new ArrayList<>(customTeam.getPlayersTeamInfo()).get(1).
                getPlayer().getPlayerAdditionalInfo().getPlayerStats());
        Assertions.assertEquals(ovrRating, RatingManager.calcReservesPlayersRating(customTeam));
        Assertions.assertEquals(0, RatingManager.calcReservesPlayersRating(officialTeam));
    }

    @Test
    void calcTeamOverallRating() {
        int ovrStartingPlayersRating = RatingManager.calcPlayerOverallRating(new ArrayList<>(customTeam.getPlayersTeamInfo()).get(0).
                getPlayer().getPlayerAdditionalInfo().getPlayerStats());
        int ovrReservesRating = RatingManager.calcPlayerOverallRating(new ArrayList<>(customTeam.getPlayersTeamInfo()).get(1).
                getPlayer().getPlayerAdditionalInfo().getPlayerStats());

        int  overallRating = ((int) Math.round((ovrStartingPlayersRating * 0.8) + (ovrReservesRating * 0.2)));
        Assertions.assertEquals(overallRating, RatingManager.calcTeamOverallRating(customTeam));
    }

}
