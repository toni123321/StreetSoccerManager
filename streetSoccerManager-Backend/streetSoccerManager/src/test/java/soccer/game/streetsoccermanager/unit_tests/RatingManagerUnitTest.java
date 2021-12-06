package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.service.AuthenticationUserDetailService;
import soccer.game.streetsoccermanager.service.RatingManager;
import soccer.game.streetsoccermanager.service.UserService;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@SpringBootTest
class RatingManagerUnitTest {

    CustomTeam team;
    @BeforeEach
    public void setUp()  {
        team = new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER"));
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
        team.setPlayersTeamInfo(playersTeamInfo.stream().collect(Collectors.toSet()));

    }

    @Test
    void calcPlayerOverallRating() {
        PlayerStats playerStats = new PlayerStats(1l, 80, 80);
        int ovrRating = (playerStats.getSkills() + playerStats.getPhysical())/2;
        Assertions.assertEquals(ovrRating, RatingManager.calcPlayerOverallRating(playerStats));
    }

    @Test
    void getStartingPlayersTeamInfo(){
        int ovrRating = RatingManager.calcPlayerOverallRating(team.getPlayersTeamInfo().
                stream().collect(Collectors.toList()).get(0).
                getPlayer().getPlayerAdditionalInfo().getPlayerStats()) / 1;
        Assertions.assertEquals(ovrRating, RatingManager.calcStartingPlayersRating(team));
    }

    @Test
    void getReservesPlayersTeamInfo(){
        int ovrRating = RatingManager.calcPlayerOverallRating(team.getPlayersTeamInfo().
                stream().collect(Collectors.toList()).get(1).
                getPlayer().getPlayerAdditionalInfo().getPlayerStats()) / 1;
        Assertions.assertEquals(ovrRating, RatingManager.calcReservesPlayersRating(team));
    }
}
