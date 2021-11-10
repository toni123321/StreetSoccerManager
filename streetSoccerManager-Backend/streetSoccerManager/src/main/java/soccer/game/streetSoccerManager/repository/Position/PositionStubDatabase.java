package soccer.game.streetSoccerManager.repository.Position;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository_interfaces.IPositionRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class PositionStubDatabase implements IPositionRepository {

    private List<Position> positions = new ArrayList<>();

    public PositionStubDatabase() {
        // add positions here
        positions.add(new Position(0l, "ATACK", "ST"));
        positions.add(new Position(1l, "ATACK", "LW"));
        positions.add(new Position(2l, "ATACK", "RW"));

        positions.add(new Position(3l, "MID", "LM"));
        positions.add(new Position(4l, "MID", "CM"));
        positions.add(new Position(5l, "MID", "RM"));

        positions.add(new Position(6l, "DEF", "LB"));
        positions.add(new Position(7l, "DEF", "CB"));
        positions.add(new Position(8l, "DEF", "RB"));

        positions.add(new Position(9l, "GK", "GK"));

        positions.add(new Position(10l, "SUB", "SUB1"));
        positions.add(new Position(11l, "SUB", "SUB2"));
        positions.add(new Position(12l, "SUB", "SUB3"));
        positions.add(new Position(13l, "SUB", "SUB4"));
        positions.add(new Position(14l, "SUB", "SUB5"));



    }

    @Override
    public List<Position> getAll() {
        return positions;
    }

    @Override
    public Position get(Long id) {
        for (Position position : positions) {
            if (position.getId().equals(id))
                return position;
        }
        return null;
    }

    @Override
    public Position get(String searchedPosition)
    {
        for (Position position : positions) {
            if (position.getPosition().equals(searchedPosition))
                return position;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        Position position = get(id);
        if (position == null){
            return false;
        }

        return positions.remove(position);
    }

    @Override
    public Boolean add(Position position) {
        if (this.get(position.getId()) != null){
            return false;
        }
        positions.add(position);
        return true;
    }

    @Override
    public Boolean update(Position position) {
        Position oldPosition = this.get(position.getId());
        if (oldPosition == null) {
            return false;
        }
        oldPosition.setCategory(position.getCategory());
        oldPosition.setPosition(position.getPosition());

        return true;
    }

    @Override
    public void deleteAll() {

    }
}
