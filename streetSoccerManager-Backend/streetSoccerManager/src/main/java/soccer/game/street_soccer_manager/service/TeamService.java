package soccer.game.street_soccer_manager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.street_soccer_manager.model.CustomTeam;
import soccer.game.street_soccer_manager.model.OfficialTeam;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.street_soccer_manager.service.serviceInterfaces.ITeamService;
import soccer.game.street_soccer_manager.model.Team;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {


    private ITeamRepository dataStore;


    public TeamService(@Qualifier("teamDatabase") ITeamRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<Team> getAll() {
        return dataStore.getAll();
    }

    @Override
    public List<Team> getCustomTeams(){
        return getAll().stream().
                filter(team -> team instanceof CustomTeam).
                collect(Collectors.toList());
    }

    @Override
    public List<Team> getOfficialTeams() {
        return getAll().stream().
                filter(team -> team instanceof OfficialTeam).
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
        //team.setId(Long.valueOf(dataStore.getAll().size()));
        team.setFormation(dataStore.getDefaultFormation());
        return dataStore.add(team);
    }

    @Override
    public Boolean update(Team team) {
        return dataStore.update(team);
    }


}
