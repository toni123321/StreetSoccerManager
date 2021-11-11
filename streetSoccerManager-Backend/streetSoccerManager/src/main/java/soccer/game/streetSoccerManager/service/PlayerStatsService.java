package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerStatsDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.model.entities.PlayerStats;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerStatsRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerStatsService;

import java.util.List;

@Service
public class PlayerStatsService implements IPlayerStatsService {
    private IPlayerStatsRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public PlayerStatsService(@Qualifier("playerStatsJPADatabase")IPlayerStatsRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<PlayerStatsDTO> getAll() {
        List<PlayerStats> playersStats = dataStore.getAll();
        List<PlayerStatsDTO> playersStatsDTO = modelMapper.map(playersStats, new TypeToken<List<PlayerStatsDTO>>() {}.getType());
        return playersStatsDTO;
    }

    @Override
    public PlayerStatsDTO get(Long id) {
        PlayerStats playerStats = dataStore.get(id);
        PlayerStatsDTO playerStatsDTO = modelMapper.map(playerStats, PlayerStatsDTO.class);
        return playerStatsDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PlayerStatsDTO add(PlayerStatsDTO stat) {
        PlayerStats playerStatsInputEntity = modelMapper.map(stat, PlayerStats.class);
        PlayerStats playerStatsOutputEntity = dataStore.add(playerStatsInputEntity);
        if(playerStatsOutputEntity != null) {
            PlayerStatsDTO playerStatsOutputDTO = modelMapper.map(playerStatsOutputEntity, PlayerStatsDTO.class);
            return playerStatsOutputDTO;
        }
        return null;
    }

    @Override
    public PlayerStatsDTO update(PlayerStatsDTO stat) {
        PlayerStats playerStatsInputEntity = modelMapper.map(stat, PlayerStats.class);
        PlayerStats playerStatsOutputEntity = dataStore.update(playerStatsInputEntity);
        if(playerStatsOutputEntity != null) {
            PlayerStatsDTO playerStatsOutputDTO = modelMapper.map(playerStatsOutputEntity, PlayerStatsDTO.class);
            return playerStatsOutputDTO;
        }
        return null;
    }
}
