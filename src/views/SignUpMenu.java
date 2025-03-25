package views;

import controllers.SignUpMenuController;
import java.util.Scanner;
import java.util.regex.Matcher;
import models.App;
import models.Result;
import models.enums.Menu;
import models.enums.SignUpMenuCommands;

/*
Explanation:
- This is a view class for the SignUpMenu.
- This class should use to check inputs and print outputs for the SignUpMenu.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */

public class SignUpMenu implements AppMenu {
    static {
        if (App.getCurrentUser() != null) App.setCurrentUser(null);
    }

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = SignUpMenuCommands.REGISTER_REGEX.getMatcher(input)) != null) {
            Result result = new SignUpMenuController().register(
                matcher.group("username"), 
                matcher.group("password"), 
                matcher.group("email"), 
                matcher.group("name")); 
            System.out.println(result.message());
            if (result.isSuccessful()) {
                App.setCurrentMenu(Menu.LOGIN_MENU.getMenu());
            }
        } 
        else if (SignUpMenuCommands.LOGIN_MENU_REGEX.getMatcher(input) != null) {
            App.setCurrentMenu(Menu.LOGIN_MENU.getMenu());        
        } 
        else {
            System.out.println("invalid command!");
        }
    }
    
}
