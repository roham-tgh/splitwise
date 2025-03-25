package views;
/*
Explanation:
- This is a view class for the ExitMenu.
- We will just use it to end the program.
 */

public class ExitMenu implements AppMenu {
    @Override
    public void show() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

}
