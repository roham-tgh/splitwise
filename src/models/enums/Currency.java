package models.enums;


/*
Explanation:
- We need to define a currency enum.
- currencies in out app are some constants that we need to define them in our code once and use them in our code.
- each currency has some data, put them here and use some methods to work with currencies so simply.
 */

public enum Currency {
    GTC(1, "GTC"),
    SUD(2, "SUD"),
    QTR(5, "QTR");

    private final int value;
    private final String name;

    Currency(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValueInGTC(int money) {
        return money * this.value;
    }

    public int getValueInUserCurrency(int money) {
        return money / this.value;
    }

    public String getString() {
        return name;
    }

    public static Currency getCurrency(String pattern) {
        try {
            return Currency.valueOf(pattern);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("currency format is invalid!");
        }
    }

}
