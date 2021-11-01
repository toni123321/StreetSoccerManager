package soccer.game.streetSoccerManager.repository.repositories.MatchInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.MatchInfo;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.IMatchInfoRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IMatchInfoJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IPositionJPARepository;

import java.util.List;

@Repository
public class MatchInfoJPADatabase implements IMatchInfoRepository {
    @Autowired
    IMatchInfoJPARepository matchInfoRepo;

    @Override
    public List<MatchInfo> getAll() {
        return matchInfoRepo.findAll();
    }

    @Override
    public MatchInfo get(Long id) {
        MatchInfo matchInfo = matchInfoRepo.findById(id).orElse(null);
        return matchInfo;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            matchInfoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(MatchInfo matchInfo) {
        if(matchInfo.getId() == null) {
            matchInfoRepo.save(matchInfo);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(MatchInfo matchInfo) {
        if(matchInfo.getId() != null) {
            matchInfoRepo.save(matchInfo);
            return true;
        }
        return false;
    }
}
