package soccer.game.streetSoccerManager.repository.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.Position;

import java.util.List;

public interface IPositionRepository {
    List<Position> getAll();
    Position get(Long id);
    Position get(String searchedPosition);
    Boolean delete(Long id);
    Boolean add(Position position);
    Boolean update(Position position);
}
