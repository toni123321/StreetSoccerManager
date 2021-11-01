package soccer.game.streetSoccerManager.model.converters;

import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.dtos.PositionDTO;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.Position;

public class PositionConverter {
    public Position convertPositionDtoToPosition(PositionDTO positionDTO){
        return new Position(positionDTO.getId(), positionDTO.getCategory(), positionDTO.getPosition());
    }
    public PositionDTO convertPositionToPositionDto(Position position) {
        return new PositionDTO(position.getId(), position.getCategory(), position.getPosition());
    }
}
