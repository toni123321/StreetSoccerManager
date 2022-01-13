package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.service.PlayMatchManager;


import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PlayMatchManagerUnitTest {

    CustomTeam customTeam;
    OfficialTeam officialTeam;

    @BeforeEach
    public void setUp() {

        customTeam = new CustomTeam(1l, "Eindhoven 19", new Formation(1l, "1-2-1"),
                new UserEntity(1l, "erick@gmail.com", "erick12345", "Erick", "Rodriguez", "Erick20", "USER"));
        officialTeam = new OfficialTeam(2l, "Barcelona", new Formation(2l, "1-2-1"), "Ronald Koeman");

        List<PlayerTeamInfo> officialTeamPlayersTeamInfo = List.of(
                new PlayerTeamInfo(1l, 10, officialTeam),
                new PlayerTeamInfo(2l, 8, officialTeam),

                new PlayerTeamInfo(3l, 6, officialTeam),
                new PlayerTeamInfo(4l, 2, officialTeam),
                new PlayerTeamInfo(5l, 1, officialTeam)
        );
        List<Player> officialTeamPlayers = List.of(
                new Player(1l,
                        new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                        new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                        new PlayerTeamInfo(1l, 10, officialTeam),
                        new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 90, 90))),
                new Player(2l,
                        new PlayerPersonalInfo(2l,"Xavi", "Hernandez", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"MID", "CM"), true),
                        new PlayerTeamInfo(2l, 8, officialTeam),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 86, 85))),
                new Player(3l,
                        new PlayerPersonalInfo(3l,"Andres", "Iniesta", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(3l, new Position(2l, "MID", "CM"), new Position(2l,"MID", "CM"), true),
                        new PlayerTeamInfo(3l, 6, officialTeam),
                        new PlayerAdditionalInfo(3l, 120, new PlayerStats(3l, 88, 83))),
                new Player(4l,
                        new PlayerPersonalInfo(4l,"Gerard", "Pique", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(4l, new Position(3l, "DEF", "CB"), new Position(3l,"DEF", "CB"), true),
                        new PlayerTeamInfo(4l, 2, officialTeam),
                        new PlayerAdditionalInfo(4l, 120, new PlayerStats(4l, 78, 82))),
                new Player(5l,
                        new PlayerPersonalInfo(5l,"Victor", "Valdez", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(5l, new Position(4l, "GK", "GK"), new Position(4l,"GK", "GK"), true),
                        new PlayerTeamInfo(5l, 1, officialTeam),
                        new PlayerAdditionalInfo(5l, 120, new PlayerStats(5l, 86, 84)))
        );

        for (int i = 0; i < officialTeamPlayers.size(); i++) {
            officialTeamPlayersTeamInfo.get(i).setPlayer(officialTeamPlayers.get(i));
        }
        officialTeam.setPlayersTeamInfo(new HashSet<>(officialTeamPlayersTeamInfo));


        List<PlayerTeamInfo> customTeamPlayersTeamInfo = List.of(
                new PlayerTeamInfo(6l, 10, customTeam),
                new PlayerTeamInfo(7l, 8, customTeam),

                new PlayerTeamInfo(8l, 6, customTeam),
                new PlayerTeamInfo(9l, 2, customTeam),
                new PlayerTeamInfo(10l, 1, customTeam)
        );
        List<Player> customTeamPlayers = List.of(
                new Player(1l,
                        new PlayerPersonalInfo(6l,"Robert", "Lewandwoski", new GregorianCalendar(1997, 5, 15)),
                        new PlayerPositionInfo(6l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                        new PlayerTeamInfo(6l, 10, customTeam),
                        new PlayerAdditionalInfo(6l, 150, new PlayerStats(6l, 90, 90))),
                new Player(2l,
                        new PlayerPersonalInfo(7l,"IlKay", "Gundogan", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(7l, new Position(2l, "MID", "CM"), new Position(2l,"MID", "CM"), true),
                        new PlayerTeamInfo(7l, 8, customTeam),
                        new PlayerAdditionalInfo(7l, 120, new PlayerStats(7l, 88, 87))),
                new Player(3l,
                        new PlayerPersonalInfo(8l,"Kevin", "De Bruyne", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(8l, new Position(2l, "MID", "CM"), new Position(2l,"MID", "CM"), true),
                        new PlayerTeamInfo(8l, 6, customTeam),
                        new PlayerAdditionalInfo(8l, 120, new PlayerStats(8l, 90, 89))),
                new Player(4l,
                        new PlayerPersonalInfo(9l,"Kyle", "Walker", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(9l, new Position(3l, "DEF", "CB"), new Position(3l,"DEF", "CB"), true),
                        new PlayerTeamInfo(9l, 2, customTeam),
                        new PlayerAdditionalInfo(9l, 120, new PlayerStats(9l, 84, 80))),
                new Player(5l,
                        new PlayerPersonalInfo(10l,"Ederson", "Moraes", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(10l, new Position(4l, "GK", "GK"), new Position(4l,"GK", "GK"), true),
                        new PlayerTeamInfo(10l, 1, customTeam),
                        new PlayerAdditionalInfo(10l, 120, new PlayerStats(10l, 80, 81)))
        );

        for (int i = 0; i < customTeamPlayers.size(); i++) {
            customTeamPlayersTeamInfo.get(i).setPlayer(customTeamPlayers.get(i));
        }
        customTeam.setPlayersTeamInfo(new HashSet<>(customTeamPlayersTeamInfo));

    }

    @Test
    void playFriendlyMatchUserHomeTeam() {
        FriendlyMatch matchOne = new FriendlyMatch(1l, customTeam, officialTeam,
                "0:0", "Match started", 0);
        Match actualMatch = PlayMatchManager.playFriendlyMatch(matchOne, "ATTACK", true);
        Match expectedMatch = new FriendlyMatch(matchOne.getId(), matchOne.getHomeTeam(), matchOne.getAwayTeam(),
                actualMatch.getResult(), matchOne.getStatistic(), 2);

        Assertions.assertEquals(expectedMatch, actualMatch);
    }

    @Test
    void playFriendlyMatchUserAwayTeam() {
        FriendlyMatch matchOne = new FriendlyMatch(1l, officialTeam, customTeam,
                "0:0", "Match started", 0);
        Match actualMatch = PlayMatchManager.playFriendlyMatch(matchOne, "OPPONENT", false);
        Match expectedMatch = new FriendlyMatch(matchOne.getId(), matchOne.getHomeTeam(), matchOne.getAwayTeam(),
                actualMatch.getResult(), matchOne.getStatistic(), 2);

        Assertions.assertEquals(expectedMatch, actualMatch);
    }
}
