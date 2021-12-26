package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PlayerService(IPlayerRepository dataStore) {
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
        if(get(id) != null) {
            dataStore.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Player add(Player player) {
        if(player.getId() == null) {
            return dataStore.add(player);
        }
        return null;
    }

    @Override
    public Player update(Player player) {
        if(player.getId() != null) {
            return dataStore.update(player);
        }
        return null;
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
