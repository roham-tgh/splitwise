package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import models.enums.Currency;

/*
Explanation:
- User is definitely an object in our app.
- put the information that you need to store about the user here.
- you can put some functions here to manage the user data too.
 */

public class User {
    private String username;
        private String password;
        private final String email;
        private final String name;
        public ArrayList<Group> groups = new ArrayList<>();
        private final HashMap<User, Integer> yourDebtToPerson = new HashMap<>(); //will be in gtc
        public Currency currency = Currency.GTC;
    
        
            
        public HashMap<User, PriorityQueue<Expense>> userToExpenses = new HashMap<>(); 
        
        public User (String username, String password, String email, String name) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.name = name;
    
        }
    
        public boolean equals(String otherUsername) {
            
            return !(this.username.equals(otherUsername));
        }
    
        public String getPassword() {
            return password;
        }

        public void setPassword(String newPassword) {
            this.password = newPassword;
        }
    
        public String getEmail() {
            return email;
        }
    
        public String getName() {
            return name;
        }
    
        public void addDebt(User user, int money) {
            if (yourDebtToPerson.containsKey(user)) {
                yourDebtToPerson.replace(user, yourDebtToPerson.get(user) + money);
            } 
            else {
                yourDebtToPerson.put(user, money);
            }
        }
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String newUsername) {
            this.username = newUsername;
    }

    public int getDebtToPerson(User user) { 
        return yourDebtToPerson.get(user);
    }

    public void payDebt(User user, int amount) {
        int oldDebt = yourDebtToPerson.get(user);
        yourDebtToPerson.replace(user, oldDebt + amount);
    }
    
}
