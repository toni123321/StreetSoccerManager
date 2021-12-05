package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.Match;
import soccer.game.streetsoccermanager.repository_interfaces.IMatchRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IMatchJPARepository;

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
        return matchRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        matchRepo.deleteById(id);
        return true;
    }

    @Override
    public Match add(Match match) {
        return matchRepo.save(match);
    }

    @Override
    public Match update(Match match) {
        return matchRepo.save(match);
    }

    @Override
    public void deleteAll() {
        matchRepo.deleteAll();
    }
}
