package views;

import java.util.Scanner;
import java.util.regex.Matcher;

import controllers.ProfileMenuController;
import models.App;
import models.Result;
import models.enums.ProfileMenuCommands;

/*
Explanation: 
- This is a view class for profile menu.
- This class should use to check inputs and print outputs for profile menu.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */


public class ProfileMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if (ProfileMenuCommands.SHOW_USER_INFO_REGEX.getMatcher(input) != null) {
            Result result = new ProfileMenuController().showUserInfo(App.getCurrentUser());
            System.out.println(result.message());
        }

        else if ((matcher = ProfileMenuCommands.CHANGE_CURRENCY_REGEX.getMatcher(input)) != null) {
            Result result = new ProfileMenuController().changeCurrency(App.getCurrentUser(), matcher.group("newCurrency"));
            System.out.println(result.message());
        }

        else if ((matcher = ProfileMenuCommands.CHANGE_USERNAME_REGEX.getMatcher(input)) != null) {
            Result result = new ProfileMenuController().changeUsername(App.getCurrentUser(), matcher.group("newUsername"));
            System.out.println(result.message());
        }

        else if ((matcher = ProfileMenuCommands.CHANGE_PASSWORD_REGEX.getMatcher(input)) != null) {
            Result result = new ProfileMenuController().changePassword(App.getCurrentUser(),
                                                                        matcher.group("oldPassword"), 
                                                                        matcher.group("newPassword"));
            System.out.println(result.message());
        }

        else if (ProfileMenuCommands.BACK_REGEX.getMatcher(input) != null) {
            App.setCurrentMenu(models.enums.Menu.DASHBOARD.getMenu());
        }

        else {
            System.out.println("invalid command!");
        }
    }
}
