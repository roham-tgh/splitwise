package models.enums;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
Explanation:
- we have commands in our dashboard and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */
public enum DashboardCommands  {
    GROUP_NAME_REGEX("[\\w!@#$%^&*\\d]{4,30}"),
    EXPENSE_TYPE_REGEX("(equally|unequally)"),
    EXPENSE_FORMAT_REGEX("\\d+"),
    CREATE_GROUP_REGEX("create-group\\s+" +
            "-n\\s+(?<groupName>\\S+)\\s+" +
            "-t\\s+(?<groupType>\\S+)"),
    SHOW_GROUPS_REGEX("show\\s+my\\s+groups"),

    ADD_USER_REGEX("add-user\\s+" +
                "-u\\s+(?<username>\\S+)\\s+" +
                "-e\\s+(?<email>\\S+)\\s+" +
                "-g\\s+(?<groupID>\\S+)"),
    ADD_EXPENSE("add-expense\\s+" +
            "-g\\s+(?<groupID>\\S+)\\s+" +
            "-s\\s+(?<distribution>(equally|unequally))\\s+" +
            "-t\\s+(?<totalExpense>\\S+)\\s+" +
            "-n\\s+(?<numberOfUsers>\\S+)"),
    SHOW_BALANCE_REGEX("show-balance\\s+" + //compare yourself with some other user
            "-u\\s+(?<username>\\S+)"),
    SETTLE_UP_REGEX("settle-up\\s+"+
            "-u\\s+(?<username>\\S+)\\s+" +
            "-m\\s+(?<money>\\S+)"),
    PROFILE_MENU_REGEX("go\\s+to\\s+profile\\s+menu"),
    LOGOUT_REGEX("logout");

            
    private final String pattern;

    DashboardCommands(String string) {
        this.pattern = string;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
