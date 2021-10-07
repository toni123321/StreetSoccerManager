package soccer.game.streetSoccerManager.interfaces.repositoryInterfaces;

import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FormationPosition;

import java.util.List;

public interface IFormationPositionRepository {
    List<FormationPosition> getAll();
    FormationPosition get(int id);
    Boolean delete(int id);
    Boolean add(FormationPosition position);
    Boolean update(FormationPosition position);
    List<FormationPosition> getAllPositionsByTeamAndFormation(int teamId, int formationId);
}
