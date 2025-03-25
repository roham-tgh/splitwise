package models.enums;
/*
Explanation:
- for handling regexes and commands we need to define an interface.
- we have different menus that each one has different commands(so different regexes).
- but after all they are all commands and we can write some part of their code once here (:
 */


import models.App;
import models.Group;
import models.User;

public interface Command {
    public static boolean isUsernameValid(String username) {
        return SignUpMenuCommands.USERNAME_REGEX.getMatcher(username).matches();
    }

    public static boolean isPasswordValid(String password) {
        return SignUpMenuCommands.PASSWORD_REGEX.getMatcher(password).matches();
    }

    public static boolean isEmailValid(String email) {
        return SignUpMenuCommands.EMAIL_REGEX.getMatcher(email).matches();
    }

    public static boolean isNameValid(String name) {
        return SignUpMenuCommands.NAME_REGEX.getMatcher(name).matches();
    }

    public static User getUser(String username) {
        for (User user : App.users) {
            if (user.equals(username)) 
                return user;
        }
        return null;
    }

    public static User getUserInGroup(String username, Group group) {
        for (User user : group.groupMembers) {
            if (user.equals(username)) 
                return user;
        }
        return null;
    }
}