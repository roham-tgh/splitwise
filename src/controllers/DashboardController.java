package controllers;

import models.App;
import models.Expense;
import models.enums.DashboardCommands;

/*
Explanation:
- This is a controller class for the dashboard Controller.
- This class will be used to implement functions that do dashboard operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

import models.Group;

import models.Result;
import models.User;
import models.enums.Command;
import models.enums.GroupType;
public class DashboardController {

    public Result createGroup(String groupName, String groupType, User groupCreator) {
    
        if (DashboardCommands.GROUP_NAME_REGEX.getMatcher(groupName) == null) {
            return new Result(false, "group name format is invalid!");
        }
        if (GroupType.GROUP_TYPE.isTypeValid(groupType) == false) {
            return new Result(false, "group type is invalid!");
        }


        Group newGroup = new Group(groupName, groupType, groupCreator);
        App.groups.add(newGroup);
        groupCreator.groups.add(newGroup);
        return new Result(true, "group created successfully!");
    }

    public String showGroups(User user) {
        StringBuilder groupsString = new StringBuilder();
        for (Group group : user.groups) {
            groupsString.append(group.toString()).append("--------------------\n");
        }
        return groupsString.toString();
    }

    public Result addUser(User groupCreator, String usernameToAdd, String emailToAdd, int groupID) {
        User userToAdd = Command.getUser(usernameToAdd);
        if (userToAdd == null) {
            return new Result(false, "user not found!");
        }
        if (!userToAdd.getEmail().equals(emailToAdd)) {
            return new Result(false, "the email provided does not match the username!");
        }
        
        if (App.groups.get(groupID - 1) == null) {
            return new Result(false, "group not found!");
        }

        if (App.groups.get(groupID - 1).isUserInGroup(userToAdd)) {
            return new Result(false, "user already in the group!");
        }

        if (!App.groups.get(groupID - 1).getGroupCreator().equals(groupCreator)) {
            return new Result(false, "only the group creator can add users!");
        }

        App.groups.get(groupID - 1).addMember(userToAdd);
        userToAdd.groups.add(App.groups.get(groupID - 1));
        return new Result(true, "user added to the group successfully!");
        
    }

    public Result addExpense(int groupID, 
                                String totalExpense, int userCount, User[] users, 
                                String[] debts, User expenseCreator, boolean isEqually) {
        if (App.groups.get(groupID - 1) == null) {
            return new Result(false, "group not found!");
        }
        for (int i = 0; i < userCount; i++) {
            if (Command.getUserInGroup(users[i].getUsername(), App.groups.get(groupID - 1)) == null) {
                return new Result(false, users[i].getUsername() + " not in group!");
            }
        }
        if ((DashboardCommands.EXPENSE_TYPE_REGEX.getMatcher(totalExpense) == null) || 
            (DashboardCommands.EXPENSE_FORMAT_REGEX.getMatcher(totalExpense) == null)) {
            return new Result(false, "expense format is invalid!");
        }
        if (isEqually) {
            createEqualExpense(expenseCreator, users, Integer.parseInt(totalExpense), userCount, App.groups.get(groupID - 1));
            return new Result(true, "expense added successfully!");
        }
        else {
            int sum = 0;
            for(int i = 0; i < userCount; i++) {
                if (DashboardCommands.EXPENSE_FORMAT_REGEX.getMatcher(debts[i]) == null) {
                    return new Result(false, "expense format is invalid!");
                }
                sum += Integer.parseInt(debts[i]);
            }
            if (sum != Integer.parseInt(totalExpense)) {
                return new Result(false, "the sum of individual costs does not equal the total cost!");
            }
            for (int i = 0; i < userCount; i++) {
                createUnequalExpense(users[i], Integer.parseInt(debts[i]), expenseCreator, App.groups.get(groupID - 1));
            }
            return new Result(true, "expense added successfully!");
        }
    }

    public Result showBalance(String username, User currentUser) {
        User user = Command.getUser(username);
        if (user == null) {
            return new Result(false, "user not found!");
        }
        int value = currentUser.currency.getValueInUserCurrency(user.getDebtToPerson(currentUser));
        if (value < 0) {
            StringBuilder groupsString = new StringBuilder();
            for (Expense expense : currentUser.userToExpenses.get(user)) {
                groupsString.append(expense.getGroup().getName()).append(", ");
            }
            groupsString.deleteCharAt(groupsString.length() - 2);
            return new Result(true, "you owe " + username + (-value) + 
                                                    currentUser.currency.getString() + "in " +
                                                     groupsString.toString());
        }
        else if (value > 0) {
            StringBuilder groupsString = new StringBuilder();
            for (Expense expense : user.userToExpenses.get(currentUser)) {
                groupsString.append(expense.getGroup().getName()).append(", ");
            }
            groupsString.deleteCharAt(groupsString.length() - 2);
            return new Result(true, username + " owes you " + value + 
                                                currentUser.currency.getString() + "in " +
                                                    groupsString.toString());
        }
        else {
            return new Result(true, " you are settled with " + username);
        }

        
    }

    public Result settleUp (String username, String inputMoney, User currentUser) {
        User user = Command.getUser(username);
        if (user == null) {
            return new Result(false, "user not found!");
        }
        if (DashboardCommands.EXPENSE_FORMAT_REGEX.getMatcher(inputMoney) == null) {
            return new Result(false, "input money format is invalid!");
        }
        int money = currentUser.currency.getValueInGTC(Integer.parseInt(inputMoney));
        if (money < 0) {
            return new Result(false, "input money format is invalid!");
        }
        boolean isMoneySpent = false;
        while (!isMoneySpent) {
            Expense expense = currentUser.userToExpenses.get(user).poll();
            if (expense == null) {
                // user.addDebt(currentUser , money);
                // idfk how ts works
            }
            else {
                if (expense.payDebt(money)) {
                    isMoneySpent = true;
                }
                else {
                    if (expense.getAmount() > 0) {
                        currentUser.userToExpenses.get(user).offer(expense);
                        isMoneySpent = true;
                    }
                    else {
                        money -= expense.getAmount();
                        //go through the process all over again
                    }
                }
            }
        }
        int debt = currentUser.currency.getValueInUserCurrency(currentUser.getDebtToPerson(user));
        String currencyString = currentUser.currency.getString();
        String resultString = (debt > 0) ? "you owe " + username + " " + debt + " " + currencyString + " now!"
                                : (debt == 0) ? "you are settled with " + username + " now!" 
                                : username + " owes you " + debt + " " + currencyString + " now!";
                                
        return new Result(true, resultString);
    }



    public void createEqualExpense(User expenseCreator, User[] users, int totalMoney, int peopleCount, Group group) {
        int moneyPerUser = expenseCreator.currency.getValueInGTC(totalMoney) / peopleCount;
        for (User user : users) {
            new Expense(expenseCreator, user, moneyPerUser, group);
        }
    }

    public void createUnequalExpense(User user, int money, User expenseCreator, Group group) {
        money = expenseCreator.currency.getValueInGTC(money); 
        new Expense(expenseCreator, user, money, group);
    }

    // public boolean userExists(User userToFind) {
    //     for (User user : this.users) {
    //         if (user.equals(userToFind)) 
    //             return true;
    //     }
    //     return false;
    // }
    
}