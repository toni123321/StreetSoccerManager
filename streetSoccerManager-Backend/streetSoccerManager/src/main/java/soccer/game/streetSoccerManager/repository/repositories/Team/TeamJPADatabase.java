package soccer.game.streetSoccerManager.repository.repositories.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Team;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IFormationJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.ITeamJPARepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeamJPADatabase implements ITeamRepository {
    @Autowired
    ITeamJPARepository teamRepo;

    @Autowired
    IFormationJPARepository formationsRepo;

    @Override
    public List<Team> getAll() {
        return teamRepo.findAll();
    }

    @Override
    public Team get(Long id) {
        Team team = teamRepo.findById(id).orElse(null);
        return team;
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            teamRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(Team team) {
        if(team.getId() == null) {
            teamRepo.save(team);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Team team) {
        if(team.getId() != null) {
            teamRepo.save(team);
            return true;
        }
        return false;
    }

    @Override
    public Formation getDefaultFormation() {
        return formationsRepo.findAll().stream().
                filter(formation -> formation.getName().equals("1-2-1")).
                collect(Collectors.toList()).get(0);
    }
}
