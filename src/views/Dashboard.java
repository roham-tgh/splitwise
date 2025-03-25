package views;

import java.util.Scanner;

import controllers.DashboardController;
import java.util.regex.Matcher;
import models.App;
import models.Result;
import models.User;
import models.enums.Command;
import models.enums.DashboardCommands;

/*
Explanation:
- This is a view class for the dashboard.
- This class should use to check inputs and print outputs for the dashboard.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */

public class Dashboard implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = DashboardCommands.CREATE_GROUP_REGEX.getMatcher(input)) != null) {
            Result result = new DashboardController().createGroup(
                matcher.group("groupName"), 
                matcher.group("groupType"), 
                App.getCurrentUser()); 
            System.out.println(result.message());
        }
        else if (DashboardCommands.SHOW_GROUPS_REGEX.getMatcher(input) != null) {
            System.out.println(new DashboardController().showGroups(App.getCurrentUser()));
        }
        else if ((matcher = DashboardCommands.ADD_USER_REGEX.getMatcher(input)) != null) {
            Result result = new DashboardController().addUser(
                App.getCurrentUser(), 
                matcher.group("username"), 
                matcher.group("email"), 
                Integer.parseInt(matcher.group("groupID"))); 
            System.out.println(result.message());
        }
        else if ((matcher = DashboardCommands.ADD_EXPENSE.getMatcher(input)) != null) {
            Result result;
            int numberOfUsers = Integer.parseInt(matcher.group("numberOfUsers"));
            int groupID = Integer.parseInt(matcher.group("groupID"));
            if (matcher.group("distribution").equals("equally")) {
                User[] users = new User[numberOfUsers];
                for (int i = 0; i < numberOfUsers; i++) {
                    users[i] = Command.getUser(scanner.nextLine()); //dont fuck up plz
                }
                result = new DashboardController()
                                .addExpense(groupID, 
                                matcher.group("totalExpense")
                                , numberOfUsers, users, null, 
                                App.getCurrentUser(), true );


            }
            else {
                User[] users = new User[numberOfUsers];
                String[] debts = new String[numberOfUsers];
                for (int i = 0; i < numberOfUsers; i++) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split("\\s+");
                    users[i] = Command.getUser(tokens[0]);
                    debts[i] = tokens[1];
                }
                result = new DashboardController()
                                .addExpense(groupID, 
                                matcher.group("totalExpense"), 
                                numberOfUsers, users, debts, 
                                App.getCurrentUser(), false);
                
            }
        
            System.out.println(result.message());
        }

        else if ((matcher = DashboardCommands.SHOW_BALANCE_REGEX.getMatcher(input)) != null) {
            Result result = new DashboardController().showBalance(matcher.group("username"), App.getCurrentUser());
            System.out.println(result.message());
        }

        else if ((matcher = DashboardCommands.SETTLE_UP_REGEX.getMatcher(input)) != null) {
            Result result = new DashboardController().settleUp(matcher.group("username"), 
                                                    matcher.group("money"), App.getCurrentUser());
            System.out.println(result.message());
        }

        else if (DashboardCommands.PROFILE_MENU_REGEX.getMatcher(input) != null) {
            System.out.println("you are now in profile menu!");
            App.setCurrentMenu(models.enums.Menu.PROFILE_MENU.getMenu());
        }

        else if (DashboardCommands.LOGOUT_REGEX.getMatcher(input) != null) {
            System.out.println("user logged out successfully.you are now in login menu!");
            App.setCurrentMenu(models.enums.Menu.LOGIN_MENU.getMenu()); //change login menu so that when you go there you get logged out 
        }
        else {
            System.out.println("invalid command!");
        }
    }

    
}
