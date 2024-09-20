package dev.melis.EngelsizGonuller.support.emialvalidator;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_PATTERN =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidEmail(String email){
        return email != null && pattern.matcher(email).matches();
    }

}
