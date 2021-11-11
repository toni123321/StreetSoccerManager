//package soccer.game.streetSoccerManager.repository.Match;
//
//import org.springframework.stereotype.Repository;
//import soccer.game.streetSoccerManager.model.entities.Match;
//import soccer.game.streetSoccerManager.repository_interfaces.IMatchRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class MatchStubDatabase implements IMatchRepository {
//    private List<Match> matches = new ArrayList<>();
//
//    public MatchStubDatabase() {
////        matches.add(new FriendlyMatch(1l, matchInfoFakeDatabase.get(1l), matchStatisticFakeDatabase.get(1l)));
////        matches.add(new FriendlyMatch(2l, matchInfoFakeDatabase.get(2l), matchStatisticFakeDatabase.get(2l)));
//    }
//
//    @Override
//    public List<Match> getAll() {
//        return matches;
//    }
//
//    @Override
//    public Match get(Long id) {
//        for (Match match : matches) {
//            if (match.getId().equals(id))
//                return match;
//        }
//        return null;
//    }
//
//    @Override
//    public Boolean delete(Long id) {
//        Match match = get(id);
//        if (match == null){
//            return false;
//        }
//        return matches.remove(match);
//    }
//
//    @Override
//    public Boolean add(Match match) {
//        if (this.get(match.getId()) != null){
//            return false;
//        }
//        matches.add(match);
//        return true;
//    }
//
//    @Override
//    public Boolean update(Match match) {
//        Match oldMatch = this.get(match.getId());
//        if (oldMatch == null) {
//            return false;
//        }
//        oldMatch.setHomeTeam(match.getHomeTeam());
//        oldMatch.setAwayTeam(match.getAwayTeam());
//        oldMatch.setResult(match.getResult());
//        oldMatch.setStatistic(match.getStatistic());
////        oldMatch.setMatchInfo(match.getMatchInfo());
////        oldMatch.setMatchStatistic(match.getMatchStatistic());
//
//        return true;
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
