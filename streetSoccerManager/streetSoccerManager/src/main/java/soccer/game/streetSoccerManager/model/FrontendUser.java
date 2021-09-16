package soccer.game.streetSoccerManager.model;

public class FrontendUser extends User {
    private String nickname;

    public FrontendUser(int id, String email, String password, String nickname) {
        super(id, email, password);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
