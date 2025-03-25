package controllers;
/*
Explanation:
- This is a controller class for the sign-up menu Controller.
- This class will be used to implement functions that do sign up menu operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */


import models.App;
import models.Result; 
import models.User;
import models.enums.Command;

public class SignUpMenuController {
    public Result register(String username, String password, String email, String name) {
        if (!Command.isUsernameValid(username)) 
            return new Result(false, "username format is invalid!");
        if (Command.getUser(username) == null) 
            return new Result(false, "this username is already taken!");
        if (!Command.isPasswordValid(password)) 
            return new Result(false, "password format is invalid!");
        if (!Command.isEmailValid(email)) 
            return new Result(false, "email format is invalid!");
        if (!Command.isNameValid(name)) 
            return new Result(false, "name format is invalid!");
        
        App.users.add(new User(username, password, email, name));
        return new Result(true, "user registered successfully.");
    }

    
}
