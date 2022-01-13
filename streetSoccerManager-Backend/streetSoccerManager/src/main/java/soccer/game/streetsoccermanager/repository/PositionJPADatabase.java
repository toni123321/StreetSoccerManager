package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.Position;
import soccer.game.streetsoccermanager.repository_interfaces.IPositionRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IPositionJPARepository;

import java.util.List;

@Repository
public class PositionJPADatabase implements IPositionRepository {

    private IPositionJPARepository positionRepo;

    @Autowired
    public PositionJPADatabase(IPositionJPARepository positionRepo) {
        this.positionRepo = positionRepo;
    }

    @Override
    public List<Position> getAll() {
        return positionRepo.findAll();
    }

    @Override
    public Position get(Long id) {
        return positionRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        positionRepo.deleteById(id);
        return true;
    }

    @Override
    public Position add(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public Position update(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public void deleteAll() {
        positionRepo.deleteAll();
    }
}
