package soccer.game.streetSoccerManager.repository.repositories.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IFormationJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.ITeamJPARepository;
import soccer.game.streetSoccerManager.repository.repositoryInterfaces.jpa.IUserJPARepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeamDatabase implements ITeamRepository {
    @Autowired
    ITeamJPARepository repo;

    @Autowired
    IFormationJPARepository formationsRepo;

    @Override
    public List<Team> getAll() {
        return repo.findAll();
    }

    @Override
    public Team get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean add(Team team) {
        if(team.getId() == null) {
            repo.save(team);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Team team) {
        if(team.getId() != null) {
            repo.save(team);
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
