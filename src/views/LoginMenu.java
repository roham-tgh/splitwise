package views;

import controllers.LoginMenuController;
import java.util.Scanner;
import java.util.regex.Matcher;
import models.App;
import models.Result;
import models.enums.LoginMenuCommands;
import models.enums.Menu;

/*
Explanation:
- This is a view class for the login menu.
- This class should use to check inputs and print outputs for the login menu.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */


public class LoginMenu implements AppMenu {
    static {
        if (App.getCurrentUser() != null) App.setCurrentUser(null);
    }
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = LoginMenuCommands.LOGIN_REGEX.getMatcher(input)) != null) {
            Result result = new LoginMenuController().login(
                matcher.group("username"), 
                matcher.group("password")); 
            System.out.println(result.message());
            if (result.isSuccessful()) {
                App.setCurrentMenu(Menu.DASHBOARD.getMenu());
            }
        }
        else if ((matcher = LoginMenuCommands.FORGET_PASSWORD_REGEX.getMatcher(input)) != null) {
            Result result = new LoginMenuController().forgetPassword(
                matcher.group("username"), 
                matcher.group("email")); 
            System.out.println(result.message());
        }
        else if (LoginMenuCommands.SIGNUP_MENU_REGEX.getMatcher(input) != null) {
            App.setCurrentMenu(Menu.SIGNUP_MENU.getMenu());        
        } 
        else {
            System.out.println("invalid command!");
        }
    }

}
