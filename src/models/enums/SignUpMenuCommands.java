package models.enums;
/*
Explanation:
- we have commands in our sign-up menu and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */
public enum SignUpMenuCommands{
    TLD("(org|com|net|edu)"),
    USERNAME_REGEX("[a-zA-Z][\\w-_.]{3,9}"),
    PASSWORD_REGEX("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,12}"),
    EMAIL_REGEX("[a-zA-Z][\\w-_.]{3,9}@[a-z]+(?:[-.][a-z]+){0,1}\\.(org|com|net|edu)"),
    REGISTER_REGEX("register -u ([a-zA-Z][\\w-_.]{3,9}) " +
            "-p (?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,12} " +
            "-e ([a-zA-Z][\\w-_.]{3,9}@[a-z]+(?:[-.][a-z]+){0,1}\\.(org|com|net|edu)) " +
            "-n (?=[a-zA-Z]+[-]?[a-zA-Z]+).*");

    private final String pattern;

    SignUpMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
