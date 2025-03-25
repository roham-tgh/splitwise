package models;
import java.lang.classfile.instruction.ThrowInstruction;
import java.util.*;
import models.enums.Menu;
import views.AppMenu;
import views.ExitMenu;

/*
Explanation:
- In out app, we need somewhere to keep our general data like list of users and list of groups and logged-in user etc.
- This class is the place for that.
- Put your general data here and use them in your code.
- you should put some functions here to manage your data too.
 */


public class App {

    public static final ArrayList<User> users = new ArrayList<>();
    public static boolean runApp = true;
    private static User currentUser = null;
    private static Menu currentMenu = Menu.SIGNUP_MENU;

    public static AppMenu getCurrentMenu() {
        return currentMenu.getMenu();
    }
    
    public static void setCurrentMenu(Menu menu) {
        if (menu.getMenu().getClass().equals(ExitMenu.class)) {
            runApp = false;
        }
        currentMenu = menu;
    }

    public User getCurrentUser() {
        return App.currentUser;
    }

    public void setCurrentUser(User user) {
        App.currentUser = user;
    }
    
    

}
