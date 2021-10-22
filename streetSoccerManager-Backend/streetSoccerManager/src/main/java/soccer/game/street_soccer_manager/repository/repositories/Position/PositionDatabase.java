package soccer.game.street_soccer_manager.repository.repositories.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.street_soccer_manager.model.Position;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.IPositionRepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa.IPositionJPARepository;

import java.util.List;

@Repository
public class PositionDatabase implements IPositionRepository {

    @Autowired
    IPositionJPARepository repo;

    @Override
    public List<Position> getAll() {
        return repo.findAll();
    }

    @Override
    public Position get(Long id) {
        if(repo.findById(id).isPresent()){
            return repo.findById(id).get();
        }
        return null;
    }

    @Override
    public Position get(String searchedPosition) {
        return repo.findFirstByPosition(searchedPosition);
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(Position position) {
        if(position.getId() == null) {
            repo.save(position);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Position position) {
        if(position.getId() != null) {
            repo.save(position);
            return true;
        }
        return false;
    }
}
