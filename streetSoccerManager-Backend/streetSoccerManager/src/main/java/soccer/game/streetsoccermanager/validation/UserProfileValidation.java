package soccer.game.streetsoccermanager.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserProfileValidation {
    private UserProfileValidation(){

    }

    private static Boolean inputValidator(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static Boolean isEmailValid(String email) {
        return inputValidator(email, "^[^@]+@[^@]+\\.[^@]+$");
    }

    public static Boolean isPasswordValid(String password) {
        return inputValidator(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }
}
