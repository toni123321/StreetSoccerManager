package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerPersonalInfoDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerPositionInfoDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.PlayerPositionInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerPositionInfoRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerPositionInfoService;

import java.util.List;

@Service
public class PlayerPositionInfoService implements IPlayerPositionInfoService {
    private IPlayerPositionInfoRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public PlayerPositionInfoService(@Qualifier("playerPositionInfoJPADatabase")IPlayerPositionInfoRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<PlayerPositionInfoDTO> getAll() {
        List<PlayerPositionInfo> playersPositionInfo = dataStore.getAll();
        List<PlayerPositionInfoDTO> playersPositionInfoDTO = modelMapper.map(playersPositionInfo, new TypeToken<List<PlayerPersonalInfoDTO>>() {}.getType());
        return playersPositionInfoDTO;
    }

    @Override
    public PlayerPositionInfoDTO get(Long id) {
        PlayerPositionInfo playerPositionInfo = dataStore.get(id);
        PlayerPositionInfoDTO playerPositionInfoDTO = modelMapper.map(playerPositionInfo, PlayerPositionInfoDTO.class);
        return playerPositionInfoDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PlayerPositionInfoDTO add(PlayerPositionInfoDTO playerPositionInfo) {
        PlayerPositionInfo playerPositionInfoInputEntity = modelMapper.map(playerPositionInfo, PlayerPositionInfo.class);
        PlayerPositionInfo playerPositionInfoOutputEntity = dataStore.add(playerPositionInfoInputEntity);
        if(playerPositionInfoOutputEntity != null) {
            PlayerPositionInfoDTO playerPositionInfoOutputDTO = modelMapper.map(playerPositionInfoOutputEntity, PlayerPositionInfoDTO.class);
            return playerPositionInfoOutputDTO;
        }
        return null;
    }

    @Override
    public PlayerPositionInfoDTO update(PlayerPositionInfoDTO playerPositionInfo) {
        PlayerPositionInfo playerPositionInfoInputEntity = modelMapper.map(playerPositionInfo, PlayerPositionInfo.class);
        PlayerPositionInfo playerPositionInfoOutputEntity = dataStore.update(playerPositionInfoInputEntity);
        if(playerPositionInfoOutputEntity != null) {
            PlayerPositionInfoDTO playerPositionInfoOutputDTO = modelMapper.map(playerPositionInfoOutputEntity, PlayerPositionInfoDTO.class);
            return playerPositionInfoOutputDTO;
        }
        return null;
    }
}
