package soccer.game.streetSoccerManager.repository.repositories.PlayerStats;

import soccer.game.streetSoccerManager.model.PlayerStats;
import soccer.game.streetSoccerManager.model.Position;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatsFakeDatabase implements IPlayerStatsRepository {

    // todo: Implement methods, add constructor with faked objects

    private List<PlayerStats> playerStats = new ArrayList<>();

    public PlayerStatsFakeDatabase() {
        playerStats.add(new PlayerStats(0l, 65));
        playerStats.add(new PlayerStats(1l, 60));
        playerStats.add(new PlayerStats(2l, 64));
        playerStats.add(new PlayerStats(3l, 65));
        playerStats.add(new PlayerStats(4l, 72));
        playerStats.add(new PlayerStats(5l, 65));
        playerStats.add(new PlayerStats(6l, 60));
        playerStats.add(new PlayerStats(7l, 64));
        playerStats.add(new PlayerStats(8l, 65));
        playerStats.add(new PlayerStats(9l, 64));
    }

    @Override
    public List<PlayerStats> getAll() {
        return playerStats;
    }

    @Override
    public PlayerStats get(Long id) {
        for (PlayerStats  stat: playerStats) {
            if (stat.getId().equals(id))
                return stat;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        PlayerStats stat = get(id);
        if (stat == null){
            return false;
        }
        return playerStats.remove(stat);
    }

    @Override
    public Boolean add(PlayerStats stat) {
        if (this.get(stat.getId()) != null){
            return false;
        }
        playerStats.add(stat);
        return true;
    }

    @Override
    public Boolean update(PlayerStats stat) {
        PlayerStats oldStat = this.get(stat.getId());
        if (oldStat == null) {
            return false;
        }
        oldStat.setOverallRating(stat.getOverallRating());
        return true;
    }
}
