package soccer.game.streetsoccermanager.unit_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerRepository;
import soccer.game.streetsoccermanager.service.PlayerService;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class PlayerServiceUnitTest {
    @Mock
    IPlayerRepository playerRepository;
    IPlayerService playerService;

    @BeforeEach
    public void setUp()  {
        List<Player> players = List.of(
                new Player(1l,
                        new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                        new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                        new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80))),
                new Player(2l,
                        new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true),
                        new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))),
                new Player(3l,
                    new PlayerPersonalInfo(3l,"Jan", "Oblak", new GregorianCalendar(1985, 5, 15)),
                    new PlayerPositionInfo(3l, new Position(3l, "GK", "GK"), new Position(3l,"GK", "GK"), true),
                    new PlayerTeamInfo(3l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                    new PlayerAdditionalInfo(3l, 180, new PlayerStats(3l, 80, 80))),
                new Player(4l,
                        new PlayerPersonalInfo(4l,"Manuel", "Neuer", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(4l, new Position(3l, "GK", "GK"), new Position(3l,"GK", "GK"), false),
                        new PlayerTeamInfo(4l, 50, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(4l, 100, new PlayerStats(4l, 83, 80))),
                new Player(5l,
                        new PlayerPersonalInfo(5l,"Erling", "Haaland", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(5l, new Position(1l, "ST", "ST"), new Position(3l,"ST", "ST"), true),
                        new PlayerTeamInfo(5l, 10, new Team(2l, "Borussia Dortmund", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(5l, 80, new PlayerStats(5l, 85, 78)))

        );
        when(playerRepository.getAll()).thenReturn(players);
        when(playerRepository.get(1l)).thenReturn(players.get(0));
        when(playerRepository.get(2l)).thenReturn(players.get(1));
        when(playerRepository.get(3l)).thenReturn(players.get(2));
        when(playerRepository.add(
                new Player(
                        new PlayerPersonalInfo(6l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                        new PlayerPositionInfo(6l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                        new PlayerTeamInfo(6l, 12, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(6l, 200, new PlayerStats(6l, 79, 84))
        ))).thenReturn(
                new Player(6l,
                        new PlayerPersonalInfo(6l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                        new PlayerPositionInfo(6l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                        new PlayerTeamInfo(6l, 12, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(6l, 200, new PlayerStats(6l, 79, 84))
                ));
        when(playerRepository.update(
                new Player(2l,
                        new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                        new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))
        )).thenReturn(
                new Player(2l,
                        new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                        new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))
        );
        playerService = new PlayerService(playerRepository);
    }

    @Test
    void GetAllPlayersSuccessScenario() {
        // Act
        List<Player> playersActual = playerService.getAll().stream().limit(2).collect(Collectors.toList());

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l,
                new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80))));
        playersExpected.add(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))));

        // Assert
        Assertions.assertEquals(playersExpected, playersActual);
    }

    @Test
    void GetAllPlayersInTeamSuccessScenario() {
        // Act
        List<Player> playersActual = playerService.getAllPlayersInTeam(1l).stream().limit(2).collect(Collectors.toList());

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l,
                new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80))));
        playersExpected.add(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))));

        // Assert
        Assertions.assertEquals(playersExpected, playersActual);
    }

    @Test
    void GetStartingPlayersSuccessScenario() {
        // Act
        List<Player> playersActual = playerService.getStartingPlayers(1l).stream().limit(2).collect(Collectors.toList());

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l,
                new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80))));
        playersExpected.add(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))));

        // Assert
        Assertions.assertEquals(playersExpected, playersActual);
    }
    @Test
    void GetReservesPlayersSuccessScenario() {
        // Act
        List<Player> playersActual = playerService.getReserves(1l).stream().limit(2).collect(Collectors.toList());

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(4l,
                new PlayerPersonalInfo(4l,"Manuel", "Neuer", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(4l, new Position(3l, "GK", "GK"), new Position(3l,"GK", "GK"), false),
                new PlayerTeamInfo(4l, 50, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(4l, 100, new PlayerStats(4l, 83, 80))));

        // Assert
        Assertions.assertEquals(playersExpected, playersActual);
    }


    @Test
    void GetAllPlayersInTeamAvailableForSwappingSuccessScenario() {
        // Act
        List<Player> playersActual = playerService.getAllPlayersInTeamAvailableForSwapping(1l, 1l);

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))));

        // Assert
        Assertions.assertEquals(playersExpected, playersActual);

        playersActual = playerService.getAllPlayersInTeamAvailableForSwapping(1l, 3l);

        playersExpected = new ArrayList<>();
        playersExpected.add(new Player(4l,
                new PlayerPersonalInfo(4l,"Manuel", "Neuer", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(4l, new Position(3l, "GK", "GK"), new Position(3l,"GK", "GK"), false),
                new PlayerTeamInfo(4l, 50, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(4l, 100, new PlayerStats(4l, 83, 80))));

        // Assert
        Assertions.assertEquals(playersExpected, playersActual);
    }


    @Test
    void GetPlayerSuccessScenario() {
        // Act
        Player player = playerService.get(1l);

        // Assert
        Assertions.assertEquals(new Player(1l,
                new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80))), player);
    }

    @Test
    void DeletePlayerSuccessScenario(){
        // Act
        playerService.delete(1l);
        verify(playerRepository).delete(1l);
        when(playerRepository.delete(2l)).thenReturn(true);
        Assertions.assertEquals(true, playerService.delete(2l));
        when(playerRepository.get(6l)).thenReturn(null);
        when(playerRepository.delete(6l)).thenReturn(false);
        Assertions.assertEquals(false, playerService.delete(6l));
    }

    @Test
    void AddPlayerSuccessScenario() {
        // Act
        Player newPlayer = playerService.add(new Player(
                new PlayerPersonalInfo(6l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                new PlayerPositionInfo(6l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                new PlayerTeamInfo(6l, 12, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(6l, 200, new PlayerStats(6l, 79, 84))));
        // Assert
        Assertions.assertEquals( new Player(6l,
                new PlayerPersonalInfo(6l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                new PlayerPositionInfo(6l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                new PlayerTeamInfo(6l, 12, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(6l, 200, new PlayerStats(6l, 79, 84))), newPlayer);
        Assertions.assertEquals(null, playerService.add(new Player(6l,
                new PlayerPersonalInfo(6l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                new PlayerPositionInfo(6l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                new PlayerTeamInfo(6l, 12, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(6l, 200, new PlayerStats(6l, 79, 84)))));
    }

    @Test
    void UpdatePlayerSuccessScenario(){
        // Act
        Player updatedPlayer = playerService.update(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))));
        // Assert
        Assertions.assertEquals(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))), updatedPlayer);
        Assertions.assertEquals(null, playerService.update(new Player(
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))));
    }
}
