package soccer.game.streetSoccerManager.config;


public class AuthenticationConfigConstants {
    private AuthenticationConfigConstants() {

    }
    public static final String SECRET = "Soccer_game";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
}
