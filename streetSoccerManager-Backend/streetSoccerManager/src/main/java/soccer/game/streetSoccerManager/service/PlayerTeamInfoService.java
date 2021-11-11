package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerTeamInfoDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.Player;
import soccer.game.streetSoccerManager.model.entities.PlayerTeamInfo;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerTeamInfoRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerTeamInfoService;

import java.util.List;

@Service
public class PlayerTeamInfoService implements IPlayerTeamInfoService {
    private IPlayerTeamInfoRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    // todo change datastore
    public PlayerTeamInfoService(@Qualifier("playerTeamInfoJPADatabase") IPlayerTeamInfoRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerTeamInfoDTO> getAll() {
        List<PlayerTeamInfo> playersTeamInfo = dataStore.getAll();
        List<PlayerTeamInfoDTO>  playersTeamInfoDTO = modelMapper.map(playersTeamInfo, new TypeToken<List<PlayerTeamInfoDTO>>() {}.getType());
        return playersTeamInfoDTO;
    }

    @Override
    public PlayerTeamInfoDTO get(Long id) {
        PlayerTeamInfo playerTeamInfo = dataStore.get(id);
        PlayerTeamInfoDTO playerTeamInfoDTO = modelMapper.map(playerTeamInfo, PlayerTeamInfoDTO.class);
        return playerTeamInfoDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PlayerTeamInfoDTO add(PlayerTeamInfoDTO playerTeamInfo) {
        PlayerTeamInfo playerTeamInfoInputEntity = modelMapper.map(playerTeamInfo, PlayerTeamInfo.class);
        PlayerTeamInfo playerTeamInfoOutputEntity = dataStore.add(playerTeamInfoInputEntity);
        if(playerTeamInfoOutputEntity != null) {
            PlayerTeamInfoDTO playerTeamInfoOutputDTO = modelMapper.map(playerTeamInfoOutputEntity, PlayerTeamInfoDTO.class);
            return playerTeamInfoOutputDTO;
        }
        return null;
    }

    @Override
    public PlayerTeamInfoDTO update(PlayerTeamInfoDTO playerTeamInfo) {
        PlayerTeamInfo playerTeamInfoInputEntity = modelMapper.map(playerTeamInfo, PlayerTeamInfo.class);
        PlayerTeamInfo playerTeamInfoOutputEntity = dataStore.update(playerTeamInfoInputEntity);
        if(playerTeamInfoOutputEntity != null) {
            PlayerTeamInfoDTO playerTeamInfoOutputDTO = modelMapper.map(playerTeamInfoOutputEntity, PlayerTeamInfoDTO.class);
            return playerTeamInfoOutputDTO;
        }
        return null;
    }
}
