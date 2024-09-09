package dev.melis.EngelsizGonuller.support.emialvalidator;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_PATTERN =
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidEmail(String email){
        return email != null && pattern.matcher(email).matches();
    }

}
