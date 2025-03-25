package views;

import java.util.Scanner;
import models.App;

/*
Explanation:
- This is a view class for the ExitMenu.
- We will just use it to end the program.
 */

public class ExitMenu implements AppMenu {
    static {
        App.runApp = false;
    }
    @Override
    public void check(Scanner scanner) {

    }
    
}
