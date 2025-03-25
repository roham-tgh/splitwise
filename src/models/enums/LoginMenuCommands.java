package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Explanation:
- we have commands in our login menu and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */
public enum LoginMenuCommands {
    LOGIN_REGEX("login\\s+" +
            "-u\\s+(?<username>\\S+)\\s+" +
            "-p\\s+(?<password>\\S+)"),
    FORGET_PASSWORD_REGEX("forget-password\\s+" +
            "-u\\s+(?<username>\\S+)\\s+" +
            "-e\\s+(?<email>\\S+)"),
    SIGNUP_MENU_REGEX("go\\s+to\\s+signup\\s+menu");

    private final String pattern;

    private LoginMenuCommands(String input) {
        this.pattern = input;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}