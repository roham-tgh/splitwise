package controllers;
/*
Explanation:
- This is a controller class for the sign-up menu Controller.
- This class will be used to implement functions that do sign up menu operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

import java.util.regex.Pattern;

import models.App;
import models.Result; 
import models.User;
import models.enums.SignUpMenuCommands;

public class SignUpMenuController {
    public Result register(String username, String password, String email, String name) {
        if (!isUsernameValid(username)) return new Result(false, "username format is invalid!");
        if (isUSernameTaken(username)) return new Result(false, "this username is already taken!");
        if (!isPasswordValid(password)) return new Result(false, "password format is invalid!");
        if (!isEmailValid(email)) return new Result(false, "email format is invalid!");
        if (!isNameValid(name)) return new Result(false, "name format is invalid!");
        App.users.add(new User(username, password, email, name));
        return new Result(true, "user registered successfully.");
    }

    private static boolean isUsernameValid(String username) {
        return Pattern.matches(SignUpMenuCommands.USERNAME_REGEX.toString(), username);
    }

    private static boolean isPasswordValid(String password) {
        return Pattern.matches(SignUpMenuCommands.PASSWORD_REGEX.toString(), password);
    }

    private static boolean isEmailValid(String email) {
        return Pattern.matches(SignUpMenuCommands.EMAIL_REGEX.toString(), email);
    }

    private static boolean isNameValid(String name) {
        return Pattern.matches(SignUpMenuCommands.NAME_REGEX.toString(), name);
    }

    private static boolean isUSernameTaken(String username) {
        for (User user : App.users) {
            if (user.equals(username)) 
                return true;
        }
        return false;
    }
}
