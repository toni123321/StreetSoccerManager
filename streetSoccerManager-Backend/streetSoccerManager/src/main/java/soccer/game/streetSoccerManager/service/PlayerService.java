package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
import soccer.game.streetSoccerManager.model.entities.Formation;
import soccer.game.streetSoccerManager.repository_interfaces.IPlayerRepository;
import soccer.game.streetSoccerManager.service_interfaces.IPlayerService;
import soccer.game.streetSoccerManager.model.entities.Player;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService {

    private IPlayerRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public PlayerService(@Qualifier("playerJPADatabase") IPlayerRepository dataStore) {
        this.dataStore = dataStore;
    }


    @Override
    public List<PlayerDTO> getAll() {
        List<Player> players = dataStore.getAll();
        List<PlayerDTO> playersDTO = modelMapper.map(players, new TypeToken<List<PlayerDTO>>() {}.getType());
        return playersDTO;
    }

    @Override
    public PlayerDTO get(Long id) {
        Player player = dataStore.get(id);
        PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);
        return playerDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }

    @Override
    public PlayerDTO add(PlayerDTO player) {
        Player playerInputEntity = modelMapper.map(player, Player.class);
        Player playerOutputEntity = dataStore.add(playerInputEntity);
        if(playerOutputEntity != null) {
            PlayerDTO playerOutputDTO = modelMapper.map(playerOutputEntity, PlayerDTO.class);
            return playerOutputDTO;
        }
        return null;
    }

    @Override
    public PlayerDTO update(PlayerDTO player) {
        Player playerInputEntity = modelMapper.map(player, Player.class);
        Player playerOutputEntity = dataStore.update(playerInputEntity);
        if(playerOutputEntity != null) {
            PlayerDTO playerOutputDTO = modelMapper.map(playerOutputEntity, PlayerDTO.class);
            return playerOutputDTO;
        }
        return null;
    }

    @Override
    public List<PlayerDTO> getAllPlayersInTeam(Long teamId) {
        return getAll().stream().filter(player ->
                player.getPlayerTeamInfo().getTeam().getId().equals(teamId)).
                collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getAllPlayersInTeamAvailableForSwapping(Long teamId, Long playerToSwapId) {
        List<PlayerDTO> playersAvailableForSwap = getAllPlayersInTeam(teamId);
        PlayerDTO playerToSwap = get(playerToSwapId);
        if(playerToSwap.getPlayerPositionInfo().getDefaultPosition().getPosition().equals("GK")) {
            playersAvailableForSwap = playersAvailableForSwap.
                    stream().filter(player ->
                            player.getPlayerPositionInfo().getDefaultPosition().getPosition().equals("GK")).
                    collect(Collectors.toList());
        }
        else{
            playersAvailableForSwap = playersAvailableForSwap.
                    stream().filter(player ->
                            !player.getPlayerPositionInfo().getDefaultPosition().getPosition().equals("GK")).
                    collect(Collectors.toList());
        }
        return playersAvailableForSwap.stream().
                filter(player -> !player.getId().equals(playerToSwapId)).collect(Collectors.toList());
    }


    @Override
    public List<PlayerDTO> getStartingPlayers(Long teamId) {
        return getAllPlayersInTeam(teamId).
                stream().
                filter(player -> player.getPlayerPositionInfo().isStarting() == true).
                collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getReserves(Long teamId) {
        return getAllPlayersInTeam(teamId).
                stream().
                filter(player -> player.getPlayerPositionInfo().isStarting() == false).
                collect(Collectors.toList());
    }


}
