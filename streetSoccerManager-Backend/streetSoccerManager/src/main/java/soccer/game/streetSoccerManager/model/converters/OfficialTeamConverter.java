//package soccer.game.streetSoccerManager.model.converters;
//
//import soccer.game.streetSoccerManager.model.dtos.OfficialTeamDTO;
//import soccer.game.streetSoccerManager.model.dtos.PlayerDTO;
//import soccer.game.streetSoccerManager.model.entities.OfficialTeam;
//import soccer.game.streetSoccerManager.model.entities.Player;
//
//public class OfficialTeamConverter {
//    public OfficialTeam convertOfficialTeamDtoToOfficialTeam(OfficialTeamDTO officialTeamDTO){
//        return new OfficialTeam(officialTeamDTO.getId(), officialTeamDTO.getName(), officialTeamDTO.getFormation(), officialTeamDTO.getManagerName());
//    }
//    public OfficialTeamDTO convertOfficialTeamToOfficialTeamDto(OfficialTeam officialTeam) {
//        return new OfficialTeamDTO(officialTeam.getId(), officialTeam.getName(), officialTeam.getFormation(), officialTeam.getManagerName());
//    }
//}
