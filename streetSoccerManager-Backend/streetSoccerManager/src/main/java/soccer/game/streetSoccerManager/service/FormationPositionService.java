package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationPositionRepository;

import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.IFormationPositionService;
import soccer.game.streetSoccerManager.model.FormationPosition;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationPositionService implements IFormationPositionService {

    @Qualifier("formationPositionFakeDatabase")
    private IFormationPositionRepository dataStore;

    public FormationPositionService( IFormationPositionRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<FormationPosition> getAllPositionsByTeamAndFormation(int teamId, int formationId){
        return dataStore.getAll().stream().
                filter(
                        position -> position.getTeam().getId() == teamId &&
                        position.getFormation().getId() == formationId).
                collect(Collectors.toList());
    }

    @Override
    public List<FormationPosition> getStartingPositionsByTeamAndFormation(int teamId, int formationId){
        return getAllPositionsByTeamAndFormation(teamId, formationId).
                stream().filter(position -> position.getPlayer().isStarting() == true).
                collect(Collectors.toList());
    }

    @Override
    public List<FormationPosition> getPositionsForReservesByTeamAndFormation(int teamId, int formationId) {
        return getAllPositionsByTeamAndFormation(teamId, formationId).
                stream().filter(position -> position.getPlayer().isStarting() == false).
                collect(Collectors.toList());
    }

    @Override
    public List<FormationPosition> getAll() {
        return dataStore.getAll();
    }

    @Override
    public FormationPosition get(int id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(int id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(FormationPosition position) {
        return dataStore.add(position);
    }

    @Override
    public Boolean update(FormationPosition position) {
        return dataStore.update(position);
    }
}
