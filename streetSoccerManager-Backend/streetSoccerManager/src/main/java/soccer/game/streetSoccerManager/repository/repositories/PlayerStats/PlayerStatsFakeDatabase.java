package soccer.game.streetSoccerManager.repository.repositories.PlayerStats;

import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerStatsRepository;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatsFakeDatabase implements IPlayerStatsRepository {

    private List<PlayerStats> playersStats = new ArrayList<>();

    public PlayerStatsFakeDatabase() {
        playersStats.add(new PlayerStats(0l, 65));
        playersStats.add(new PlayerStats(1l, 60));
        playersStats.add(new PlayerStats(2l, 64));
        playersStats.add(new PlayerStats(3l, 65));
        playersStats.add(new PlayerStats(4l, 72));
        playersStats.add(new PlayerStats(5l, 65));
        playersStats.add(new PlayerStats(6l, 60));
        playersStats.add(new PlayerStats(7l, 64));
        playersStats.add(new PlayerStats(8l, 65));
        playersStats.add(new PlayerStats(9l, 64));
    }

    @Override
    public List<PlayerStats> getAll() {
        return playersStats;
    }

    @Override
    public PlayerStats get(Long id) {
        for (PlayerStats  stat: playersStats) {
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
        return playersStats.remove(stat);
    }

    @Override
    public Boolean add(PlayerStats stat) {
        if (this.get(stat.getId()) != null){
            return false;
        }
        playersStats.add(stat);
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
