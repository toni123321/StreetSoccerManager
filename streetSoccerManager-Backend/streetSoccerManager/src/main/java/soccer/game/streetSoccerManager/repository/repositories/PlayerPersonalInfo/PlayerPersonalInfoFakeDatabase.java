package soccer.game.streetSoccerManager.repository.repositories.PlayerPersonalInfo;

import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IPlayerPositionInfoRepository;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class PlayerPersonalInfoFakeDatabase implements IPlayerPersonalInfoRepository {
    private List<PlayerPersonalInfo> playersPersonalInfo = new ArrayList<>();

    public PlayerPersonalInfoFakeDatabase() {
        playersPersonalInfo.add(new PlayerPersonalInfo(1l, "Javi","Diaz", new GregorianCalendar(1997, 5, 15)));
        playersPersonalInfo.add(new PlayerPersonalInfo(2l, "Andres","Dumitrescu", new GregorianCalendar(2001, 3, 11)));

    }

    @Override
    public List<PlayerPersonalInfo> getAll() {
        return playersPersonalInfo;
    }

    @Override
    public PlayerPersonalInfo get(Long id) {
        for (PlayerPersonalInfo playerPersonalInfo : playersPersonalInfo) {
            if (playerPersonalInfo.getId().equals(id))
                return playerPersonalInfo;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        PlayerPersonalInfo playerPersonalInfo = get(id);
        if (playerPersonalInfo == null){
            return false;
        }

        return playersPersonalInfo.remove(playerPersonalInfo);
    }

    @Override
    public Boolean add(PlayerPersonalInfo playerPersonalInfo) {
        if (this.get(playerPersonalInfo.getId()) != null){
            return false;
        }
        playersPersonalInfo.add(playerPersonalInfo);
        return true;
    }

    @Override
    public Boolean update(PlayerPersonalInfo playerPersonalInfo) {
        PlayerPersonalInfo oldPlayerPersonalInfo = this.get(playerPersonalInfo.getId());
        if (oldPlayerPersonalInfo == null) {
            return false;
        }
        oldPlayerPersonalInfo.setFirstName(playerPersonalInfo.getFirstName());
        oldPlayerPersonalInfo.setLastName(playerPersonalInfo.getLastName());
        oldPlayerPersonalInfo.setDob(playerPersonalInfo.getDob());

        return true;
    }
}
