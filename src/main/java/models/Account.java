package models;

import java.util.Objects;

public class Account {
    private String accountID;
    private String accountHolderName;
    private AccountStatus accountStatus;
    private AccountType accountType;
    private double accountBalance;

    public Account(String accountID, String accountHolderName, AccountStatus accountStatus, AccountType accountType, double accountBalance) {
        this.accountID = accountID;
        this.accountHolderName = accountHolderName;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%f", accountID, accountHolderName, accountStatus.name(), accountType.name(), accountBalance);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return account.getAccountID().equals(this.accountID);
    }
}