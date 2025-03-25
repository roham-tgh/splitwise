

import views.AppView;

public class Main {

    public static void main(String[] args) {
      
      System.out.println("Program started");
        try {
          // Your existing code here
          AppView appView = new AppView();
          
          appView.run();
            System.out.println("Program completed successfully");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
      
    }
  }
