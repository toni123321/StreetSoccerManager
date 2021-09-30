package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.interfaces.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.model.Team;

import java.util.List;

@Service
public class TeamService implements ITeamService {

    @Qualifier("teamFakeDatabase")
    private ITeamRepository dataStore;


    public TeamService(ITeamRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<Team> getAll() {
        return dataStore.getAll();
    }

    @Override
    public Team get(int id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(int id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(Team team) {

        return dataStore.add(team);
    }

    @Override
    public Boolean update(Team team) {
        return dataStore.update(team);
    }


}
