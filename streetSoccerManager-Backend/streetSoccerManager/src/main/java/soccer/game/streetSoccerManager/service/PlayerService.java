package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IPlayerService;
import soccer.game.streetSoccerManager.model.Player;

import java.util.List;

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
        return dataStore.add(player);
    }

    @Override
    public Boolean update(Player player) {
       return dataStore.update(player);
    }

   
}
