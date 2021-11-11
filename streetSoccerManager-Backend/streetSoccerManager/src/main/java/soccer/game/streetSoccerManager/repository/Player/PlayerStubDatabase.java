//package soccer.game.streetSoccerManager.repository.Player;
//
//import lombok.Getter;
//import org.springframework.stereotype.Repository;
//import soccer.game.streetSoccerManager.model.entities.*;
//import soccer.game.streetSoccerManager.repository.PlayerAdditionalInfo.PlayerAdditionalInfoStubDatabase;
//import soccer.game.streetSoccerManager.repository.PlayerPersonalInfo.PlayerPersonalInfoStubDatabase;
//import soccer.game.streetSoccerManager.repository.PlayerPositionInfo.PlayerPositionInfoStubDatabase;
//import soccer.game.streetSoccerManager.repository.PlayerTeamInfo.PlayerTeamInfoStubDatabase;
//import soccer.game.streetSoccerManager.repository.Team.TeamStubDatabase;
//import soccer.game.streetSoccerManager.repository_interfaces.*;
//
//import java.util.*;
//
//@Repository
//public class PlayerStubDatabase implements IPlayerRepository {
//    @Getter
//    private List<Player> players = new ArrayList<>();
//
//    private ITeamRepository teamFakeDatabase = new TeamStubDatabase();
//    List<Team> teams = teamFakeDatabase.getAll();
//
//    private IPlayerPersonalInfoRepository playerPersonalInfoRepository = new PlayerPersonalInfoStubDatabase();
//    private IPlayerPositionInfoRepository playerPositionInfoRepository = new PlayerPositionInfoStubDatabase();
//    private IPlayerTeamInfoRepository playerTeamInfoRepository = new PlayerTeamInfoStubDatabase();
//    private IPlayerAdditionalInfoRepository playerAdditionalInfoRepository = new PlayerAdditionalInfoStubDatabase();
//
//
//    public PlayerStubDatabase() {
//        //STARTING
//        players.add(new Player(1l, playerPersonalInfoRepository.get(1l), playerPositionInfoRepository.get(1l), playerTeamInfoRepository.get(1l), playerAdditionalInfoRepository.get(1l)));
//        players.add(new Player(2l, playerPersonalInfoRepository.get(2l), playerPositionInfoRepository.get(2l), playerTeamInfoRepository.get(2l), playerAdditionalInfoRepository.get(2l)));
//    }
//
//
//    @Override
//    public List<Player> getAll() {
//        Collections.sort(players);
//        return players;
//
//    }
//
//    @Override
//    public Player get(Long id) {
//        for (Player player : players) {
//            if (player.getId().equals(id))
//                return player;
//        }
//        return null;
//    }
//
//    @Override
//    public Boolean delete(Long id) {
//        Player player = get(id);
//        if (player == null){
//            return false;
//        }
//
//        return players.remove(player);
//    }
//
//    @Override
//    public Boolean add(Player player) {
//        if (this.get(player.getId()) != null){
//            return false;
//        }
//        players.add(player);
//        return true;
//    }
//
//    @Override
//    public Boolean update(Player player) {
//        Player oldPlayer = this.get(player.getId());
//        if (oldPlayer == null) {
//            return false;
//        }
//        oldPlayer.setPlayerPersonalInfo(player.getPlayerPersonalInfo());
//        oldPlayer.setPlayerPositionInfo(player.getPlayerPositionInfo());
//        oldPlayer.setPlayerTeamInfo(player.getPlayerTeamInfo());
//        oldPlayer.setPlayerAdditionalInfo(player.getPlayerAdditionalInfo());
//        return true;
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
