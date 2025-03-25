package controllers;

import models.App;
import models.Result;
import models.User;
import models.enums.Command;

/*
Explanation:
- This is a controller class for the login menu Controller.
- This class will be used to implement functions that do log in menu operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

public class LoginMenuController {
    public Result login(String username, String password) {
        User user = Command.getUser(username);
        if (user  == null) {
            return new Result(false, "username doesn't exist!");
        }
        if (user.getPassword().equals(password)) {
            App.setCurrentUser(user);
            return new Result(true, "user logged in successfully.you are now in dashboard!");
        } 
        else {
            return new Result(false, "password is incorrect!");
        }
    }
    

    public Result forgetPassword(String username, String email) {
        User user = Command.getUser(username);
        if (user  == null) {
            return new Result(false, "username doesn't exist!");
        }
        if (user.getEmail().equals(email)) {
            return new Result(false, "password : " + user.getPassword());
        } 
        else {
            return new Result(false, "email is incorrect!");
        }
    }
}