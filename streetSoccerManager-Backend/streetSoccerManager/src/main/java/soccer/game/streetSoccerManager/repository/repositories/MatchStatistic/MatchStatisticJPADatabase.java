package soccer.game.streetSoccerManager.repository.repositories.MatchStatistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.MatchStatistic;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchStatisticRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IMatchStatisticJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPositionJPARepository;

import java.util.List;

@Repository
public class MatchStatisticJPADatabase implements IMatchStatisticRepository {

    @Autowired
    IMatchStatisticJPARepository matchStatisticRepo;

    @Override
    public List<MatchStatistic> getAll() {
        return matchStatisticRepo.findAll();
    }

    @Override
    public MatchStatistic get(Long id) {
        MatchStatistic matchStatistic = matchStatisticRepo.findById(id).orElse(null);
        return matchStatistic;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            matchStatisticRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(MatchStatistic matchStatistic) {
        if(matchStatistic.getId() == null) {
            matchStatisticRepo.save(matchStatistic);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(MatchStatistic matchStatistic) {
        if(matchStatistic.getId() != null) {
            matchStatisticRepo.save(matchStatistic);
            return true;
        }
        return false;
    }
}
