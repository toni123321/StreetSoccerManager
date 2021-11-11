//package soccer.game.streetSoccerManager.repository.PlayerAdditionalInfo;
//
//import org.springframework.stereotype.Repository;
//import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
//import soccer.game.streetSoccerManager.repository.PlayerStats.PlayerStatsStubDatabase;
//import soccer.game.streetSoccerManager.repository_interfaces.IPlayerAdditionalInfoRepository;
//import soccer.game.streetSoccerManager.repository_interfaces.IPlayerStatsRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class PlayerAdditionalInfoStubDatabase implements IPlayerAdditionalInfoRepository {
//    private List<PlayerAdditionalInfo> playersAdditionalInfo = new ArrayList<>();
//    private IPlayerStatsRepository playerStatsFakeDatabase = new PlayerStatsStubDatabase();
//
//    public PlayerAdditionalInfoStubDatabase() {
//        playersAdditionalInfo.add(new PlayerAdditionalInfo(1l, 150, playerStatsFakeDatabase.get(0l)));
//        playersAdditionalInfo.add(new PlayerAdditionalInfo(2l, 120, playerStatsFakeDatabase.get(1l)));
//    }
//
//    @Override
//    public List<PlayerAdditionalInfo> getAll() {
//        return playersAdditionalInfo;
//    }
//
//    @Override
//    public PlayerAdditionalInfo get(Long id) {
//        for (PlayerAdditionalInfo playerAdditionalInfo : playersAdditionalInfo) {
//            if (playerAdditionalInfo.getId().equals(id))
//                return playerAdditionalInfo;
//        }
//        return null;
//    }
//
//
//    @Override
//    public Boolean delete(Long id) {
//        PlayerAdditionalInfo playerAdditionalInfo = get(id);
//        if (playerAdditionalInfo == null){
//            return false;
//        }
//
//        return playersAdditionalInfo.remove(playerAdditionalInfo);
//    }
//
//    @Override
//    public Boolean add(PlayerAdditionalInfo playerAdditionalInfo) {
//        if (this.get(playerAdditionalInfo.getId()) != null){
//            return false;
//        }
//        playersAdditionalInfo.add(playerAdditionalInfo);
//        return true;
//    }
//
//    @Override
//    public Boolean update(PlayerAdditionalInfo playerAdditionalInfo) {
//        PlayerAdditionalInfo oldPlayerAdditionalInfo = this.get(playerAdditionalInfo.getId());
//        if (oldPlayerAdditionalInfo == null) {
//            return false;
//        }
//        oldPlayerAdditionalInfo.setPrice(playerAdditionalInfo.getPrice());
//        oldPlayerAdditionalInfo.setPlayerStats(playerAdditionalInfo.getPlayerStats());
//
//        return true;
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
