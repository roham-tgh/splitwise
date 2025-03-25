package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Explanation:
- we have commands in our profile menu and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */

public enum ProfileMenuCommands {
    SHOW_USER_INFO_REGEX("show\\s+user\\s+info"),
    CHANGE_CURRENCY_REGEX("change-currency\\s+-n\\s+(?<newCurrency>\\S+)"),
    CHANGE_USERNAME_REGEX("change-username\\s+-n\\s+(?<newUsername>\\S+)"),
    CHANGE_PASSWORD_REGEX("change-password\\s+-o\\s+(?<oldPassword>\\S+)\\s+-n\\s+(?<newPassword>\\S+"),
    BACK_REGEX("back");

    private final String pattern;

    private ProfileMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.matches()) 
            return matcher;
        else   
            return null;
    }
    
}
