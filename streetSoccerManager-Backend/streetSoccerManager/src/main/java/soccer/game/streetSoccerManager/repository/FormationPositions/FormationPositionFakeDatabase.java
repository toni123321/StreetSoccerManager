package soccer.game.streetSoccerManager.repository.FormationPositions;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.interfaces.repositoryInterfaces.IFormationPositionRepository;
import soccer.game.streetSoccerManager.model.Formation;
import soccer.game.streetSoccerManager.model.FormationPosition;
import soccer.game.streetSoccerManager.model.Player;
import soccer.game.streetSoccerManager.model.Team;
import soccer.game.streetSoccerManager.repository.Formation.FormationFakeDatabase;
import soccer.game.streetSoccerManager.repository.Player.PlayerFakeDatabase;
import soccer.game.streetSoccerManager.repository.Team.TeamFakeDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Getter
public class FormationPositionFakeDatabase implements IFormationPositionRepository {

    private FormationFakeDatabase formationFakeDatabase = new FormationFakeDatabase();
    private List<Formation> formations = formationFakeDatabase.getAll();

    private TeamFakeDatabase teamFakeDatabase = new TeamFakeDatabase();
    private List<Team> teams = teamFakeDatabase.getAll();

    private PlayerFakeDatabase playerFakeDatabase = new PlayerFakeDatabase();
    private List<Player> players = playerFakeDatabase.getAll();

    private List<FormationPosition> positions = new ArrayList<>();

    public FormationPositionFakeDatabase() {
        positions.add(new FormationPosition(0, "GK",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(0)));
        positions.add(new FormationPosition(1, "DEF",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(1)));
        positions.add(new FormationPosition(2, "MID",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(2)));
        positions.add(new FormationPosition(3, "MID",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(3)));
        positions.add(new FormationPosition(4, "ST",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(4)));


        positions.add(new FormationPosition(5, "Sub1",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(5)));
        positions.add(new FormationPosition(6, "Sub2",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(6)));
        positions.add(new FormationPosition(7, "Sub3",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(7)));
        positions.add(new FormationPosition(8, "Sub4",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(8)));
        positions.add(new FormationPosition(9, "Sub5",
                formationFakeDatabase.get(1), teamFakeDatabase.get(0), 1f, 1f, playerFakeDatabase.get(9)));


    }


    @Override
    public List<FormationPosition> getAll() {
        return positions;
    }

    @Override
    public FormationPosition get(int id){
        for (FormationPosition position : positions) {
            if (position.getId() == id)
                return position;
        }
        return null;
    }

    @Override
    public Boolean delete(int id) {
        FormationPosition position = get(id);
        if (position == null){
            return false;
        }
        return formations.remove(position);

    }

    @Override
    public Boolean add(FormationPosition position) {
        if (this.get(position.getId()) != null){
            return false;
        }
        positions.add(position);
        return true;
    }

    @Override
    public Boolean update(FormationPosition position) {
        FormationPosition oldPosition = this.get(position.getId());
        if (oldPosition == null) {
            return false;
        }
        oldPosition.setName(position.getName());
        oldPosition.setFormation(position.getFormation());
        oldPosition.setTeam(position.getTeam());
        oldPosition.setX_cor(position.getX_cor());
        oldPosition.setY_cor(position.getY_cor());
        oldPosition.setPlayer(position.getPlayer());

        return true;
    }
}
