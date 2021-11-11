package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerAdditionalInfoDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.PlayerAdditionalInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerAdditionalInfoRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerAdditionalInfoService;

import java.util.List;

@Service
public class PlayerAdditionalInfoService implements IPlayerAdditionalInfoService {
    private IPlayerAdditionalInfoRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    // todo change datastore
    public PlayerAdditionalInfoService(@Qualifier("playerAdditionalInfoJPADatabase") IPlayerAdditionalInfoRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerAdditionalInfoDTO> getAll() {
        List<PlayerAdditionalInfo> playersAdditionalInfo = dataStore.getAll();
        List<PlayerAdditionalInfoDTO> playersAdditionalInfoDTO = modelMapper.map(playersAdditionalInfo, new TypeToken<List<PlayerAdditionalInfoDTO>>() {}.getType());
        return playersAdditionalInfoDTO;
    }

    @Override
    public PlayerAdditionalInfoDTO get(Long id) {
        PlayerAdditionalInfo playerAdditionalInfo = dataStore.get(id);
        PlayerAdditionalInfoDTO playerAdditionalInfoDTO = modelMapper.map(playerAdditionalInfo, PlayerAdditionalInfoDTO.class);
        return playerAdditionalInfoDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PlayerAdditionalInfoDTO add(PlayerAdditionalInfoDTO playerAdditionalInfo) {
        PlayerAdditionalInfo playerAdditionalInfoInputEntity = modelMapper.map(playerAdditionalInfo, PlayerAdditionalInfo.class);
        PlayerAdditionalInfo playerAdditionalInfoOutputEntity = dataStore.add(playerAdditionalInfoInputEntity);
        if(playerAdditionalInfoOutputEntity != null) {
            PlayerAdditionalInfoDTO playerAdditionalInfoOutputDTO = modelMapper.map(playerAdditionalInfoOutputEntity, PlayerAdditionalInfoDTO.class);
            return playerAdditionalInfoOutputDTO;
        }
        return null;
    }

    @Override
    public PlayerAdditionalInfoDTO update(PlayerAdditionalInfoDTO playerAdditionalInfo) {
        PlayerAdditionalInfo playerAdditionalInfoInputEntity = modelMapper.map(playerAdditionalInfo, PlayerAdditionalInfo.class);
        PlayerAdditionalInfo playerAdditionalInfoOutputEntity = dataStore.update(playerAdditionalInfoInputEntity);
        if(playerAdditionalInfoOutputEntity != null) {
            PlayerAdditionalInfoDTO playerAdditionalInfoOutputDTO = modelMapper.map(playerAdditionalInfoOutputEntity, PlayerAdditionalInfoDTO.class);
            return playerAdditionalInfoOutputDTO;
        }
        return null;
    }
}
