package soccer.game.streetSoccerManager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.entities.CustomTeam;
import soccer.game.streetSoccerManager.model.entities.OfficialTeam;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.service.serviceInterfaces.ITeamService;
import soccer.game.streetSoccerManager.model.entities.Team;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {


    private ITeamRepository dataStore;


    public TeamService(@Qualifier("teamJPADatabase") ITeamRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<Team> getAll() {
        return dataStore.getAll();
    }

    @Override
    public List<Team> getCustomTeams(){
        return getAll().stream().
                filter(CustomTeam.class::isInstance).
                collect(Collectors.toList());
    }

    @Override
    public List<Team> getOfficialTeams() {
        return getAll().stream().
                filter(OfficialTeam.class::isInstance).
                collect(Collectors.toList());
    }

    @Override
    public Team get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public Boolean add(Team team) {
        team.setFormation(dataStore.getDefaultFormation());
        return dataStore.add(team);
    }

    @Override
    public Boolean update(Team team) {
        return dataStore.update(team);
    }


}
