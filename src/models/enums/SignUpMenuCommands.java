package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
Explanation:
- we have commands in our sign-up menu and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */
public enum SignUpMenuCommands{
    USERNAME_REGEX("[a-zA-Z][\\w-_.]{3,9}"),
    PASSWORD_REGEX("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,12}"),
    EMAIL_REGEX("[a-zA-Z][\\w-_.]{3,9}@[a-z]+(?:[-.][a-z]+){0,1}\\.(org|com|net|edu)"),
    NAME_REGEX("\\w+[-]?\\w+"),
    REGISTER_REGEX("register\\s+" +
            "-u\\s+(?<username>\\S+)\\s+" +
            "-p\\s+(?<password>\\S+)\\s+" +
            "-e\\s+(?<email>\\S+)\\s+" +
            "-n\\s+(?<name>\\S+)"),
    LOGIN_MENU_REGEX("go\\s+to\\s+login\\s+menu");

    private final String pattern;

    SignUpMenuCommands(String pattern) {

        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
