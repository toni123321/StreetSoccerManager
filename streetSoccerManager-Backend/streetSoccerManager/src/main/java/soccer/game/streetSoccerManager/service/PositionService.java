package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PositionDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Position;
import soccer.game.streetSoccerManager.repository_interfaces.IPositionRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPositionService;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    private IPositionRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public PositionService(@Qualifier("positionJPADatabase") IPositionRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PositionDTO> getAll() {
        List<Position> positions = dataStore.getAll();
        List<PositionDTO> positionsDTO = modelMapper.map(positions, new TypeToken<List<PositionDTO>>() {}.getType());
        return positionsDTO;
    }

    @Override
    public PositionDTO get(Long id) {
        Position position = dataStore.get(id);
        PositionDTO positionDTO = modelMapper.map(position, PositionDTO.class);
        return positionDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PositionDTO add(PositionDTO position) {
        Position positionInputEntity = modelMapper.map(position, Position.class);
        Position positionOutputEntity = dataStore.add(positionInputEntity);
        if(positionOutputEntity != null) {
            PositionDTO positionOutputDTO = modelMapper.map(positionOutputEntity, PositionDTO.class);
            return positionOutputDTO;
        }
        return null;
    }

    @Override
    public PositionDTO update(PositionDTO position) {
        Position positionInputEntity = modelMapper.map(position, Position.class);
        Position positionOutputEntity = dataStore.update(positionInputEntity);
        if(positionOutputEntity != null) {
            PositionDTO positionOutputDTO = modelMapper.map(positionOutputEntity, PositionDTO.class);
            return positionOutputDTO;
        }
        return null;
    }
}
