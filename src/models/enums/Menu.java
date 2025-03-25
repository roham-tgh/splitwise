package models.enums;

import java.util.Scanner;
import views.AppMenu;
import views.*;

/*
Explanation:
- In your code, you have some menus that are constants, and we move between them.
- a good way to handle this is to use enums to define them and use them in your code.
 */
public enum Menu {
    LOGIN_MENU(new LoginMenu()),
    SIGNUP_MENU(new SignUpMenu()),
    DASHBOARD_MENU(new Dashboard()),
    PROFILE_MENU(new ProfileMenu()),
    EXIT_MENU(new ExitMenu());

    AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void setMenu(AppMenu menu) {
        this.menu = menu;
    }
    
    public AppMenu getMenu() {
        return this.menu;
    }

    public void check(Scanner scanner) {
        this.menu.check(scanner);
    }
}
