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
                        new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))
        );
        when(playerRepository.getAll()).thenReturn(players);
        when(playerRepository.get(1l)).thenReturn(players.get(0));
        when(playerRepository.get(2l)).thenReturn(players.get(1));
        when(playerRepository.add(
                new Player(
                        new PlayerPersonalInfo(3l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                        new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                        new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(3l, 200, new PlayerStats(1l, 79, 84))
        ))).thenReturn(
                        new Player(3l,
                            new PlayerPersonalInfo(3l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                            new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                            new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                            new PlayerAdditionalInfo(3l, 200, new PlayerStats(1l, 79, 84))
                ));
        when(playerRepository.update(
                new Player(2l,
                        new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                        new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))
        )).thenReturn(
                new Player(2l,
                        new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                        new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                        new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                        new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))
        );
        playerService = new PlayerService(playerRepository);
    }

    @Test
    void GetAllPlayersSuccessScenario() {
        // Act
        List<Player> playersActual = playerService.getAll();

        List<Player> playersExpected = new ArrayList<>();
        playersExpected.add(new Player(1l,
                new PlayerPersonalInfo(1l,"Lionel", "Messi", new GregorianCalendar(1997, 5, 15)),
                new PlayerPositionInfo(1l, new Position(1l, "ATACK", "ST"), new Position(1l,"ATACK", "ST"), true),
                new PlayerTeamInfo(1l, 10, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(1l, 150, new PlayerStats(1l, 70, 80))));
        playersExpected.add(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "DEF", "CB"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))));

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
        when(playerRepository.get(3l)).thenReturn(null);
        when(playerRepository.delete(3l)).thenReturn(false);
        Assertions.assertEquals(false, playerService.delete(3l));
    }

    @Test
    void AddPlayerSuccessScenario() {
        // Act
        Player newPlayer = playerService.add( new Player(
                new PlayerPersonalInfo(3l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(3l, 200, new PlayerStats(1l, 79, 84))));
        // Assert
        Assertions.assertEquals( new Player(3l,
                new PlayerPersonalInfo(3l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(3l, 200, new PlayerStats(1l, 79, 84))), newPlayer);
        Assertions.assertEquals(null, playerService.add( new Player(3l,
                new PlayerPersonalInfo(3l, "Luis", "Suarez", new GregorianCalendar(1987, 5, 15)),
                new PlayerPositionInfo(3l, new Position(3l, "ATACK", "LW"), new Position(3l,"ATACK", "LW"), false),
                new PlayerTeamInfo(3l, 6, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(3l, 200, new PlayerStats(1l, 79, 84)))));
    }

    @Test
    void UpdatePlayerSuccessScenario(){
        // Act
        Player updatedPlayer = playerService.update(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))));
        // Assert
        Assertions.assertEquals(new Player(2l,
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76))), updatedPlayer);
        Assertions.assertEquals(null, playerService.update(new Player(
                new PlayerPersonalInfo(2l,"Juan", "Mata", new GregorianCalendar(1985, 5, 15)),
                new PlayerPositionInfo(2l, new Position(2l, "MID", "CM"), new Position(2l,"DEF", "CB"), true),
                new PlayerTeamInfo(2l, 1, new Team(1l, "Barcelona", new Formation(1l, "1-2-1"))),
                new PlayerAdditionalInfo(2l, 120, new PlayerStats(2l, 82, 76)))));
    }
}
