package soccer.game.street_soccer_manager.repository.repositories.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.street_soccer_manager.model.Formation;
import soccer.game.street_soccer_manager.model.Team;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.ITeamRepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa.IFormationJPARepository;
import soccer.game.street_soccer_manager.repository.repositoryInterfaces.jpa.ITeamJPARepository;

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
