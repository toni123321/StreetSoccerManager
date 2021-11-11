package soccer.game.streetSoccerManager.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import soccer.game.streetSoccerManager.model.dtos.FormationDTO;
import soccer.game.streetSoccerManager.model.dtos.UserDTO;
import soccer.game.streetSoccerManager.model.entities.User;
import soccer.game.streetSoccerManager.repository_interfaces.IFormationRepository;
import soccer.game.streetSoccerManager.service_interfaces.IFormationService;
import soccer.game.streetSoccerManager.model.entities.Formation;

import java.util.List;

@Service
public class FormationService implements IFormationService {
    private IFormationRepository dataStore;
    private ModelMapper modelMapper = new ModelMapper();

    public FormationService(@Qualifier("formationJPADatabase") IFormationRepository dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<FormationDTO> getAll() {
        List<Formation> formations = dataStore.getAll();
        List<FormationDTO> formationDTOs = modelMapper.map(formations, new TypeToken<List<FormationDTO>>() {}.getType());
        return formationDTOs;
    }

    @Override
    public FormationDTO get(Long id) {
        Formation formation = dataStore.get(id);
        FormationDTO formationDTO = modelMapper.map(formation, FormationDTO.class);
        return formationDTO;
    }

    @Override
    public Boolean delete(Long id) {
        return dataStore.delete(id);
    }


    @Override
    public FormationDTO add(FormationDTO formation) {
        Formation formationInputEntity = modelMapper.map(formation, Formation.class);
        Formation formationOutputEntity = dataStore.add(formationInputEntity);
        if(formationOutputEntity != null) {
            FormationDTO formationOutputDTO = modelMapper.map(formationOutputEntity, FormationDTO.class);
            return formationOutputDTO;
        }
        return null;
    }

    @Override
    public FormationDTO update(FormationDTO formation) {
        Formation formationInputEntity = modelMapper.map(formation, Formation.class);
        Formation formationOutputEntity = dataStore.update(formationInputEntity);
        if(formationOutputEntity != null) {
            FormationDTO formationOutputDTO = modelMapper.map(formationOutputEntity, FormationDTO.class);
            return formationOutputDTO;
        }
        return null;
    }

    @Override
    public void deleteAll() {
        dataStore.deleteAll();
    }
}

