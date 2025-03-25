package models;

import java.util.PriorityQueue;

/*
Explanation:
- when we create an expense, we need to store some information about it.
- Expense is something that we need to make it an object.
- put those information here and use them in your code.
 */

public class Expense {
    
    private final User expenseCreator;
    private final User personInDebt;
    private int amount;
    private final Group group;
    
    
    
    public Expense(User expenseCreator , User personInDebt,  int amount, Group group) {
        this.expenseCreator = expenseCreator;
        this.amount = amount;
        this.group = group;
        this.personInDebt = personInDebt;
        addExpense();
        personInDebt.addDebt(expenseCreator, amount);
        expenseCreator.addDebt(personInDebt, -amount);
    }
    
    private void addExpense() {
        if (personInDebt.userToExpenses.containsKey(expenseCreator)) {
            personInDebt.userToExpenses.get(expenseCreator).offer(this);
        } 
        else {
            personInDebt.userToExpenses.put(expenseCreator, new PriorityQueue<>(
                (a, b) -> Integer.compare(a.expenseIndex(), b.expenseIndex())));
            personInDebt.userToExpenses.get(expenseCreator).offer(this);
        }
    }
    
    private int expenseIndex() {
        return personInDebt.groups.indexOf(this.group);
    }

    public Group getGroup() {
        return this.group;
    }

    public boolean payDebt(int money) { //return true if debt is paid, false if debt still remains (positive or negative)
        amount -= money;
        personInDebt.payDebt(expenseCreator, money);
        expenseCreator.payDebt(personInDebt, -money);
        return (amount == 0);
    }

    public int getAmount() {
        return amount;
    }
}
