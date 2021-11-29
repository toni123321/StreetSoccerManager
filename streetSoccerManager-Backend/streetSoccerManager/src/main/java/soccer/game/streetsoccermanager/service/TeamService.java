package soccer.game.streetsoccermanager.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetsoccermanager.model.entities.CustomTeam;
import soccer.game.streetsoccermanager.model.entities.OfficialTeam;
import soccer.game.streetsoccermanager.model.entities.PlayerTeamInfo;
import soccer.game.streetsoccermanager.repository_interfaces.ITeamRepository;
import soccer.game.streetsoccermanager.service_interfaces.ITeamService;
import soccer.game.streetsoccermanager.model.entities.Team;

import java.util.ArrayList;
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
    public Team getTeamByUserId(Long id){
        return getAll().stream()
                .filter(team -> ((CustomTeam) team).getManager().getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }

    @Override
    public Team get(Long id) {
        return dataStore.get(id);
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    public int calcTeamRating(Team team){
        int rating = 0;
        List<PlayerTeamInfo> playersTeamInfo = new ArrayList<>();
        if( team.getPlayersTeamInfo() != null) {
            playersTeamInfo = team.getPlayersTeamInfo().stream().filter(p -> p.getPlayer().getPlayerPositionInfo().isStarting()).collect(Collectors.toList());
        }
        for (PlayerTeamInfo playerTeamInfo: playersTeamInfo) {
//            rating += playerTeamInfo.getPlayer().getPlayerAdditionalInfo().getPlayerStats().getOverallRating();

        }
        if(playersTeamInfo.isEmpty()){
            rating /= playersTeamInfo.size();
        }
        return rating;
    }

    @Override
    public Team add(Team team) {
//        team.setFormation(dataStore.getDefaultFormation());
//        team.setRating(calcTeamRating(team));
//        return dataStore.add(team);
        return dataStore.add(team);

    }

    @Override
    public Team update(Team team) {
        return dataStore.update(team);

    }


}
