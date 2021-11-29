package soccer.game.streetsoccermanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soccer.game.streetsoccermanager.model.entities.*;
import soccer.game.streetsoccermanager.repository_interfaces.ITeamRepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.IFormationJPARepository;
import soccer.game.streetsoccermanager.repository_interfaces.jpa.ITeamJPARepository;

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
        return teamRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        if(get(id) != null) {
            Team team = get(id);
            teamRepo.delete(team);
            return true;

        }
        return false;
    }

    @Override
    public Team add(Team team) {
        if(team.getId() == null) {
            return teamRepo.save(team);
        }
        return null;
    }

    @Override
    public Team update(Team team) {
        if(team.getId() != null) {
            return teamRepo.save(team);
        }
        return null;
    }

    @Override
    public Formation getDefaultFormation() {
        return formationsRepo.findAll().stream().
                filter(formation -> formation.getName().equals("1-2-1")).
                collect(Collectors.toList()).get(0);
    }

    @Override
    public void deleteAll() {
        formationsRepo.deleteAll();
    }
}
