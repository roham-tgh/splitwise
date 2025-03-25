package models;
import java.util.*;
import models.enums.Menu;
import views.AppMenu;

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
    private static AppMenu currentMenu = Menu.SIGNUP_MENU.getMenu();
    public static ArrayList<Group> groups = new ArrayList<>();

    public static AppMenu getCurrentMenu() {
        return currentMenu;
    }
    
    public static void setCurrentMenu(AppMenu menu) {
        currentMenu = menu;
    }

    public static User getCurrentUser() {
        return App.currentUser;
    }

    public static void setCurrentUser(User user) {
        App.currentUser = user;
    }
    
    

}
