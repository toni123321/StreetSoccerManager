package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.IPlayerService;
import soccer.game.streetSoccerManager.model.Player;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService {

    @Qualifier("playerFakeDatabase")
    private IPlayerRepository dataStore;

    public PlayerService(IPlayerRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<Player> getAll() {
        return dataStore.getAll();
    }



    @Override
    public Player get(int id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(int id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(Player player) {
        player.setId(dataStore.getAll().size());
        return dataStore.add(player);
    }

    @Override
    public Boolean update(Player player) {
       return dataStore.update(player);
    }

    @Override
    public List<Player> getAllPlayersInTeam(int teamId) {
        return getAll().stream().filter(player ->
                player.getTeam().getId() == teamId).
                collect(Collectors.toList());
    }

    @Override
    public List<Player> getAllPlayersInTeamAvailableForSwapping(int teamId, int playerToSwapId) {
        List<Player> playersAvailableForSwap = getAllPlayersInTeam(teamId);
        Player playerToSwap = get(playerToSwapId);
        if(playerToSwap.getDefaultPosition().equals("GK")) {
            playersAvailableForSwap = playersAvailableForSwap.
                    stream().filter(player ->
                            player.getDefaultPosition().equals("GK")).
                    collect(Collectors.toList());
        }
        else{
            playersAvailableForSwap = playersAvailableForSwap.
                    stream().filter(player ->
                            !player.getDefaultPosition().equals("GK")).
                    collect(Collectors.toList());
        }
        return playersAvailableForSwap.stream().
                filter(player -> player.getId() != playerToSwapId).collect(Collectors.toList());
    }


    @Override
    public List<Player> getStartingPlayers(int teamId) {
        return getAllPlayersInTeam(teamId).
                stream().
                filter(player -> player.isStarting() == true).
                collect(Collectors.toList());
    }

    @Override
    public List<Player> getReserves(int teamId) {
        return getAllPlayersInTeam(teamId).
                stream().
                filter(player -> player.isStarting() == false).
                collect(Collectors.toList());
    }


}
