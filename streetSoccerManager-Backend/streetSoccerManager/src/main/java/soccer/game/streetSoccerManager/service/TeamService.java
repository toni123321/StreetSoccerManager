package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.TeamDTO;
import soccer.game.streetSoccerManager.model.entities.*;
import soccer.game.streetSoccerManager.repository_interfaces.ITeamRepository;
import soccer.game.streetSoccerManager.service_interfaces.ITeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {
    private ITeamRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public TeamService(@Qualifier("teamJPADatabase") ITeamRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<TeamDTO> getAll() {
        List<Team> teams = dataStore.getAll();
        List<TeamDTO> teamDTOs = modelMapper.map(teams, new TypeToken<List<TeamDTO>>() {}.getType());
        return teamDTOs;
    }

    @Override
    public List<TeamDTO> getCustomTeams(){
        return getAll().stream().
                filter(CustomTeam.class::isInstance).
                collect(Collectors.toList());
    }

    @Override
    public List<TeamDTO> getOfficialTeams() {
        return getAll().stream().
                filter(OfficialTeam.class::isInstance).
                collect(Collectors.toList());
    }

    @Override
    public TeamDTO get(Long id) {
        Team team = dataStore.get(id);
        TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
        return teamDTO;
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
        if(playersTeamInfo.size() != 0){
            rating /= playersTeamInfo.size();
        }
        return rating;
    }

    @Override
    public TeamDTO add(TeamDTO team) {
//        team.setFormation(dataStore.getDefaultFormation());
//        team.setRating(calcTeamRating(team));
//        return dataStore.add(team);
        Team teamInputEntity = modelMapper.map(team, Team.class);
        Team teamOutputEntity = dataStore.add(teamInputEntity);
        if(teamOutputEntity != null) {
            TeamDTO teamOutputDTO = modelMapper.map(teamOutputEntity, TeamDTO.class);
            return teamOutputDTO;
        }
        return null;
    }

    @Override
    public TeamDTO update(TeamDTO team) {
        Team teamInputEntity = modelMapper.map(team, Team.class);
        Team teamOutputEntity = dataStore.update(teamInputEntity);
        if(teamOutputEntity != null) {
            TeamDTO teamOutputDTO = modelMapper.map(teamOutputEntity, TeamDTO.class);
            return teamOutputDTO;
        }
        return null;
    }


}
