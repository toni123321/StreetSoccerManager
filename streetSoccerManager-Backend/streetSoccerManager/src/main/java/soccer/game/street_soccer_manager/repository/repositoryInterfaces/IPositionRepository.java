package soccer.game.street_soccer_manager.repository.repositoryInterfaces;

import soccer.game.street_soccer_manager.model.Position;

import java.util.List;

public interface IPositionRepository {
    List<Position> getAll();
    Position get(Long id);
    Position get(String searchedPosition);
    Boolean delete(Long id);
    Boolean add(Position position);
    Boolean update(Position position);
}
