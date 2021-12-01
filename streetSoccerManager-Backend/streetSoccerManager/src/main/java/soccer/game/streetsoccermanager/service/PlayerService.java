package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.repository_interfaces.IPlayerRepository;
import soccer.game.streetsoccermanager.service_interfaces.IPlayerService;
import soccer.game.streetsoccermanager.model.entities.Player;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService {

    private IPlayerRepository dataStore;

    public PlayerService(@Qualifier("playerJPADatabase") IPlayerRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<Player> getAll() {
        return dataStore.getAll();
    }



    @Override
    public Player get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Player add(Player player) {
        return dataStore.add(player);
    }

    @Override
    public Player update(Player player) {
        return dataStore.update(player);
    }

    @Override
    public List<Player> getAllPlayersInTeam(Long teamId) {
        return getAll().stream().filter(player ->
                        player.getPlayerTeamInfo().getTeam().getId().equals(teamId)).
                collect(Collectors.toList());
    }

    @Override
    public List<Player> getAllPlayersInTeamAvailableForSwapping(Long teamId, Long playerToSwapId) {
        List<Player> playersAvailableForSwap = getAllPlayersInTeam(teamId);
        Player playerToSwap = get(playerToSwapId);
        if(playerToSwap.getPlayerPositionInfo().getDefaultPosition().getName().equals("GK")) {
            playersAvailableForSwap = playersAvailableForSwap.
                    stream().filter(player ->
                            player.getPlayerPositionInfo().getDefaultPosition().getName().equals("GK")).
                    collect(Collectors.toList());
        }
        else{
            playersAvailableForSwap = playersAvailableForSwap.
                    stream().filter(player ->
                            !player.getPlayerPositionInfo().getDefaultPosition().getName().equals("GK")).
                    collect(Collectors.toList());
        }
        return playersAvailableForSwap.stream().
                filter(player -> !player.getId().equals(playerToSwapId)).collect(Collectors.toList());
    }


    @Override
    public List<Player> getStartingPlayers(Long teamId) {
        return getAllPlayersInTeam(teamId).
                stream().
                filter(player -> player.getPlayerPositionInfo().isStarting()).
                collect(Collectors.toList());
    }

    @Override
    public List<Player> getReserves(Long teamId) {
        return getAllPlayersInTeam(teamId).
                stream().
                filter(player -> !player.getPlayerPositionInfo().isStarting()).
                collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }


}
