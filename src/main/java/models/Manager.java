package models;

import java.io.*;
import java.util.*;

public class Manager {
    private List<Account> accounts;
    private BufferedWriter writer;
    private BufferedReader reader;
    private File accountsFile;

    public Manager() {
        accounts = new ArrayList<>();
        accountsFile = new File("Accounts.txt");
        try {
            if (!accountsFile.exists()) {
                accountsFile.createNewFile();
            }

            reader = new BufferedReader(new FileReader(accountsFile));
            String line;
            boolean hasContent = false;

            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split("\\|");
                String accountID = accountData[0];
                String accountName = accountData[1];
                AccountStatus accountStatus = AccountStatus.valueOf(accountData[2]);
                AccountType accountType = AccountType.valueOf(accountData[3]);
                double balance = Double.parseDouble(accountData[4]);

                accounts.add(new Account(accountID, accountName, accountStatus, accountType, balance));
                hasContent = true;
            }

            if (!hasContent) {
                System.out.println("The file is empty.");
            }

            writer = new BufferedWriter(new FileWriter(accountsFile,true));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addAccount(String accountID, String accountName, String accountType, String accountStatus, double balance) {
        boolean isValid = isAccountIDExist(accountID);
        if (isValid) {
            System.out.println("Already Exsit");
        } else {
            Account newAccount = new Account(accountID, accountName, AccountStatus.valueOf(accountStatus), AccountType.valueOf(accountType), balance);
            accounts.add(newAccount);

            try {
                writer.write(String.format("%s|%s|%s|%s|%f%n", accountID, accountName, accountStatus, accountType, balance));
                writer.flush();

                System.out.println("Account added successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean removeAccount(String accountID) {
        Iterator<Account> iterator = accounts.iterator();
        boolean found = false;
        boolean removed=false;
        while (iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getAccountID().equals(accountID)) {
                iterator.remove();
                removed=true;
                found = true;
                break;
            }
        }

        if (found) {
            updateFile(accountsFile);
            System.out.println("Account removed and file updated successfully.");
        } else {
            System.out.println("Account with ID " + accountID + " not found.");
        }
        return removed;
    }
    public Account searchAccount(String accountID) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                return account;
            }
        }
        return null;
    }
    public void sortByBalance() {
        accounts.sort((o1, o2) -> Double.compare(o1.getAccountBalance(), o2.getAccountBalance()));
        updateFile(accountsFile);
    }
    public void sortByAccountName() {
        accounts.sort((o1, o2) -> o1.getAccountHolderName().compareTo(o2.getAccountHolderName()));
        updateFile(accountsFile);
    }
    public boolean updateAccount(String accountID, String newName, double newBalance, AccountStatus newStatus,AccountType accountType) {
        boolean found=false;
        boolean updated=false;
        for (Account arrayAccounts:accounts){
            if(arrayAccounts.getAccountID().equals(accountID)){
                found=true;
                arrayAccounts.setAccountID(accountID);
                arrayAccounts.setAccountHolderName(newName);
                arrayAccounts.setAccountType(accountType);
                arrayAccounts.setAccountBalance(newBalance);
                arrayAccounts.setAccountStatus(newStatus);
                updateFile(accountsFile);
                updated=true;
                return updated;

            }else{
                found=false;
            }
        }
        return updated;

    }
    public boolean deposit(String accountID, double amount) {
        Account account = searchAccount(accountID);
        if (account != null && amount > 0) {
            account.setAccountBalance(account.getAccountBalance() + amount);
            updateFile(accountsFile);
            return true;
        } else {
            System.out.println("Invalid account or amount.");
            return false;
        }
    }
    public boolean withdraw(String accountID, double amount) {
        Account account = searchAccount(accountID);
        if (account != null && amount > 0 && account.getAccountBalance() >= amount) {
            account.setAccountBalance(account.getAccountBalance() - amount);
            updateFile(accountsFile);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid account.");
            return false;
        }
    }
    public void closeAccount(Account account){
       for (Account listaccount:accounts){
           if(listaccount.getAccountID().equals(account.getAccountID())){
               listaccount.setAccountStatus(AccountStatus.CLOSED);
               break;
           }
       }
       updateFile(accountsFile);

    }
    public String[] getSummary() {
        String[] summary = new String[6];
        String totalAccounts = String.valueOf(accounts.size());
        String TotalActiveAccounts = "";
        String TotalBalance;
        String TotalBusinessAccounts = "";
        String TotalSavingAccounts = "";
        String TotalCheckingAccounts;
        int activeAccount = 0;
        int savingAccounts = 0;
        int checkingAccounts = 0;
        int businessAccounts = 0;
        double balance = 0;
        for (Account account : accounts) {
            balance += account.getAccountBalance();
            if (account.getAccountStatus().name().equals("ACTIVE")) {
                activeAccount++;
            }
            if (account.getAccountType().name().equals("SAVING")) {
                savingAccounts++;
            } else if (account.getAccountType().name().equals("CHECKING")) {
                checkingAccounts++;
            } else {
                businessAccounts++;
            }


        }
        TotalActiveAccounts = String.valueOf(activeAccount);
        TotalBalance = String.valueOf(balance);
        TotalSavingAccounts = String.valueOf(savingAccounts);
        TotalBusinessAccounts = String.valueOf(businessAccounts);
        TotalCheckingAccounts = String.valueOf(checkingAccounts);
        summary[0] = totalAccounts;
        summary[1] = TotalActiveAccounts;
        summary[2] = TotalBusinessAccounts;
        summary[3] = TotalSavingAccounts;
        summary[4] = TotalCheckingAccounts;
        summary[5] = TotalBalance;

        return summary;


    }
    public boolean closeAccount(String accountID) {
        Account account = searchAccount(accountID);
        if (account != null) {
            account.setAccountStatus(AccountStatus.CLOSED);
            updateFile(accountsFile);
            return true;
        } else {
            System.out.println("Account not found.");
            return false;
        }
    }
    public void updateFile(File accountFile) {
        try {
            if (accountFile.exists()) {
                accountFile.delete();
            }

            accountFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(accountFile));
            for (Account account : accounts) {
                writer.write(String.format("%s|%s|%s|%s|%f%n", account.getAccountID(), account.getAccountHolderName(),
                        account.getAccountStatus(), account.getAccountType(), account.getAccountBalance()));
            }
            writer.flush();
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isAccountIDExist(String accountID) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                return true;
            }
        }
        return false;
    }
    public void closeResources() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}