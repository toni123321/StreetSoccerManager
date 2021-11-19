package soccer.game.streetSoccerManager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import soccer.game.streetSoccerManager.service_interfaces.ITeamService;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
@RestController
@RequestMapping("/officialTeams")
public class OfficialTeamController {
    @Qualifier("teamService")
    private ITeamService teamService;
    private ModelMapper modelMapper;

    public OfficialTeamController(ITeamService teamService) {
        this.teamService = teamService;
        this.modelMapper = new ModelMapper();
    }

}
