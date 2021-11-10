package soccer.game.streetSoccerManager.model.dtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nickname;
    private int points;

    public UserDTO(Long id, String email, String password, String firstName, String lastName, String nickname, int points) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.points = points;
    }
}
