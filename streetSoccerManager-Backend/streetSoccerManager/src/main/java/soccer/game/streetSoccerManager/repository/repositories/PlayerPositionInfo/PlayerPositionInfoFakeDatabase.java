package soccer.game.streetSoccerManager.repository.repositories.PlayerPositionInfo;

import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository.repositories.Position.PositionFakeDatabase;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPositionInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPositionRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerPositionInfoFakeDatabase implements IPlayerPositionInfoRepository {
    private List<PlayerPositionInfo> playersPositionInfo = new ArrayList<>();
    private IPositionRepository positionFakeDatabase = new PositionFakeDatabase();

    public PlayerPositionInfoFakeDatabase() {
        playersPositionInfo.add(new PlayerPositionInfo(1l, 11, positionFakeDatabase.get(10l), positionFakeDatabase.get(10l), true));
        playersPositionInfo.add(new PlayerPositionInfo(2l, 8, positionFakeDatabase.get(8l), positionFakeDatabase.get(8l), true));

        playersPositionInfo.add(new PlayerPositionInfo(3l, 17, positionFakeDatabase.get(2l), positionFakeDatabase.get(15l), false));

    }

    @Override
    public List<PlayerPositionInfo> getAll() {
        return playersPositionInfo;
    }

    @Override
    public PlayerPositionInfo get(Long id) {
        for (PlayerPositionInfo playerPositionInfo : playersPositionInfo) {
            if (playerPositionInfo.getId().equals(id))
                return playerPositionInfo;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        PlayerPositionInfo playerPositionInfo = get(id);
        if (playerPositionInfo == null){
            return false;
        }

        return playersPositionInfo.remove(playerPositionInfo);
    }

    @Override
    public Boolean add(PlayerPositionInfo playerPositionInfo) {
        if (this.get(playerPositionInfo.getId()) != null){
            return false;
        }
        playersPositionInfo.add(playerPositionInfo);
        return true;
    }

    @Override
    public Boolean update(PlayerPositionInfo playerPositionInfo) {
        PlayerPositionInfo oldPlayerPositionInfo = this.get(playerPositionInfo.getId());
        if (oldPlayerPositionInfo == null) {
            return false;
        }
        oldPlayerPositionInfo.setPositionIndex(playerPositionInfo.getPositionIndex());
        oldPlayerPositionInfo.setDefaultPosition(playerPositionInfo.getDefaultPosition());
        oldPlayerPositionInfo.setCurrentPosition(playerPositionInfo.getCurrentPosition());
        oldPlayerPositionInfo.setStarting(playerPositionInfo.isStarting());
        return true;
    }
}
