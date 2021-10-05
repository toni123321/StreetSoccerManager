package soccer.game.streetSoccerManager.interfaces.serviceInterfaces;

import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FormationPosition;

import java.util.List;

public interface IFormationPositionService {
    List<FormationPosition> getAll();
    FormationPosition get(int id);
    Boolean delete(int id);
    Boolean add(FormationPosition position);
    Boolean update(FormationPosition position);
    List<FormationPosition> getAllPositionsByFormation(int formationId);
}
