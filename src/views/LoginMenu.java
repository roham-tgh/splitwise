package views;

import java.util.Scanner;

/*
Explanation:
- This is a view class for the login menu.
- This class should use to check inputs and print outputs for the login menu.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */


public class LoginMenu implements AppMenu {
    static {
        System.out.println("you are now in login menu!");
    }
    @Override
    public void check(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
