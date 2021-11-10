package soccer.game.streetSoccerManager.repository.Match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.repository_interfaces.IMatchRepository;
import soccer.game.streetSoccerManager.repository_interfaces.jpa.IMatchJPARepository;

import java.util.List;

@Repository
public class MatchJPADatabase implements IMatchRepository {
    @Autowired
    IMatchJPARepository matchRepo;

    @Override
    public List<Match> getAll() {
        return matchRepo.findAll();
    }

    @Override
    public Match get(Long id) {
        Match match = matchRepo.findById(id).orElse(null);
        return match;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            matchRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(Match match) {
        if(match.getId() == null) {
            matchRepo.save(match);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Match match) {
        if(match.getId() != null) {
            matchRepo.save(match);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        matchRepo.deleteAll();
    }
}
