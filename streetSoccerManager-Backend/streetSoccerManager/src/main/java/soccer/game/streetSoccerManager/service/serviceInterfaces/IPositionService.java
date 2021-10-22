package soccer.game.streetSoccerManager.service.serviceInterfaces;

import soccer.game.streetSoccerManager.model.Position;
import soccer.game.streetSoccerManager.model.Team;

import java.util.List;

public interface IPositionService {
    List<Position> getAll();
    Position get(Long id);
    Boolean delete(Long id);
    Boolean add(Position position);
    Boolean update(Position position);
}
