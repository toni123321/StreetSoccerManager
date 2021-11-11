package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerPersonalInfoDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerPersonalInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerPersonalInfoRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerPersonalInfoService;

import java.util.List;

@Service
public class PlayerPersonalInfoService implements IPlayerPersonalInfoService {
    private IPlayerPersonalInfoRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    // todo change datastore
    public PlayerPersonalInfoService(@Qualifier("playerPersonalInfoJPADatabase") IPlayerPersonalInfoRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerPersonalInfoDTO> getAll() {
        List<PlayerPersonalInfo> playersPersonalInfo = dataStore.getAll();
        List<PlayerPersonalInfoDTO> playersPersonalInfoDTO = modelMapper.map(playersPersonalInfo, new TypeToken<List<PlayerPersonalInfoDTO>>() {}.getType());
        return playersPersonalInfoDTO;
    }

    @Override
    public PlayerPersonalInfoDTO get(Long id) {
        PlayerPersonalInfo playerPersonalInfo = dataStore.get(id);
        PlayerPersonalInfoDTO playerPersonalInfoDTO = modelMapper.map(playerPersonalInfo, PlayerPersonalInfoDTO.class);
        return playerPersonalInfoDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PlayerPersonalInfoDTO add(PlayerPersonalInfoDTO playerPersonalInfo) {
        PlayerPersonalInfo playerPersonalInfoInputEntity = modelMapper.map(playerPersonalInfo, PlayerPersonalInfo.class);
        PlayerPersonalInfo playerPersonalInfoOutputEntity = dataStore.add(playerPersonalInfoInputEntity);
        if(playerPersonalInfoOutputEntity != null) {
            PlayerPersonalInfoDTO playerPersonalInfoOutputDTO = modelMapper.map(playerPersonalInfoOutputEntity, PlayerPersonalInfoDTO.class);
            return playerPersonalInfoOutputDTO;
        }
        return null;
    }

    @Override
    public PlayerPersonalInfoDTO update(PlayerPersonalInfoDTO playerPersonalInfo) {
        PlayerPersonalInfo playerPersonalInfoInputEntity = modelMapper.map(playerPersonalInfo, PlayerPersonalInfo.class);
        PlayerPersonalInfo playerPersonalInfoOutputEntity = dataStore.update(playerPersonalInfoInputEntity);
        if(playerPersonalInfoOutputEntity != null) {
            PlayerPersonalInfoDTO playerPersonalInfoOutputDTO = modelMapper.map(playerPersonalInfoOutputEntity, PlayerPersonalInfoDTO.class);
            return playerPersonalInfoOutputDTO;
        }
        return null;
    }
}
