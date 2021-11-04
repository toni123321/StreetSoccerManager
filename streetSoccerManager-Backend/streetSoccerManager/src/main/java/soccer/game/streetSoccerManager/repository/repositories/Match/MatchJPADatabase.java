package soccer.game.streetSoccerManager.repository.repositories.Match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Match;
import soccer.game.streetSoccerManager.model.entities.Team;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IFormationJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IMatchJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.ITeamJPARepository;

import java.util.List;
import java.util.stream.Collectors;

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
}
