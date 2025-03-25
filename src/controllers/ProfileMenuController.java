package controllers;
/*
Explanation:
- This is a controller class for the profile menu Controller.
- This class will be used to implement functions that do profile menu operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

import models.Result;
import models.User;
import models.enums.Command;
import models.enums.Currency;
import models.enums.SignUpMenuCommands;

public class ProfileMenuController {
    
    public Result showUserInfo(User user) {
        String info = "username: " + user.getUsername() + "\n" +
                "password: " + user.getPassword() + "\n" +
                "currency: " + user.currency.getString() + "\n" +
                "email: " + user.getEmail() + "\n" +
                "name " + user.getName();

        return new Result(true, info);
    }

    public Result changeCurrency(User user, String newCurrency) {
        try {
            user.currency = Currency.getCurrency(newCurrency);
            return new Result(true, "your currency changed to " + user.currency.getString() + " successfully!");
        } catch (IllegalArgumentException e) {
            return new Result(false, e.getMessage());
        }
    }

    public Result changeUsername(User user, String newUsername) {
        if (newUsername.equals(user.getUsername())) {
            return new Result(false, "please enter a new username!");
        }

        if (Command.getUser(newUsername) != null) {
            return new Result(false, "this username is already taken!");
        }

        if (SignUpMenuCommands.USERNAME_REGEX.getMatcher(newUsername) == null) {
            return new Result(false, "new username format is invalid!");
        }

        user.setUsername(newUsername);
        return new Result(true, "your username changed to " + user.getUsername() + " successfully!");
    }

    public Result changePassword(User user, String oldPassword, String newPassword) {
        if (!oldPassword.equals(user.getPassword())) {
            return new Result(false, "password incorrect!");
        }

        if (oldPassword.equals(newPassword)) {
            return new Result(false, "please enter a new password!");
        }

        if (SignUpMenuCommands.PASSWORD_REGEX.getMatcher(newPassword) == null) {
            return new Result(false, "new password format is invalid!");
        }

        user.setPassword(newPassword);
        return new Result(true, "your password changed successfully!");
    }
}
