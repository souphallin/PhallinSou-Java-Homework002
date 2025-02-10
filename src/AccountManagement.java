
import java.util.Random;
import java.util.Scanner;

public class AccountManagement{
    static final String red = "\u001b[31m";
    static final String green = "\u001b[32m";
    static final String yellow = "\u001b[33m";
    static final String reset = "\u001b[0m";

    static Account currentAccount;
    static Account[] accounts = new Account[10]; // Limited to 10 accounts for simplicity
    static int accountCount = 0;  // To keep track of the number of created accounts

    public static void createAccount(Scanner scanner){

        System.out.println(yellow + "===============| CREATING ACCOUNT |===============" + reset);

        if (accountCount < accounts.length) {
            System.out.println("1. CHECKING ACCOUNT");
            System.out.println("2. SAVING ACCOUNT");
            System.out.print("Which account that you want to create?? : ");
            int type = scanner.nextInt();
            scanner.nextLine();

            // Generate a random account number
            Random random = new Random();
            int accountNumber = 100000 + random.nextInt(900000); // Generates a 6-digit number

            System.out.print("Enter username: ");
            String userName = scanner.nextLine();

            System.out.print("Enter date of birth (DD/MM/YYYY): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Enter gender: ");
            String gender = scanner.nextLine();

            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();

            double balance = 0; // Initial balance set to 0

            Account newAccount;
            if (type == 1) {
                newAccount = new CheckingAccount(accountNumber, userName, dateOfBirth, gender, phoneNumber, balance);
            } else {
                newAccount = new SavingAccount(accountNumber, userName, dateOfBirth, gender, phoneNumber, balance);
            }

            accounts[accountCount++] = newAccount;
            currentAccount = newAccount;
            System.out.println(green + "Account created successfully! Your account number is: " + accountNumber + reset);
        } else {
            System.out.println(red + "Account limit reached!" + reset);
        }

    }
    public static void depositMoney(Scanner scanner){
        if (currentAccount == null) {
            System.out.println(red + "No account created yet! Please create an account first." + reset);
            return;
        }
        System.out.println(yellow + "===============| DEPOSIT MONEY |===============" + reset);
        System.out.println("1. CHECKING ACCOUNT");
        System.out.println("2. SAVING ACCOUNT");
        System.out.print("Choose an option : ");
        int option = scanner.nextInt();
        System.out.print("Enter amount to deposit : ");
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
    }

    public static void withdrawMoney(Scanner scanner){
        if (currentAccount == null) {
            System.out.println(red + "No account created yet! Please create an account first." + reset);
            return;
        }
        System.out.println(yellow + "===============| WITHDRAW MONEY |===============" + reset);
        System.out.println("1. CHECKING ACCOUNT");
        System.out.println("2. SAVING ACCOUNT");
        System.out.print("Choose an option : ");
        int option = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        currentAccount.withdraw(amount);
    }

    public static void transferMoney(Scanner scanner) {
        if (accountCount < 2) {
            System.out.println(red + "At least two accounts are needed to transfer money!" + reset);
            return;
        }

        System.out.println(yellow + "===============| TRANSFER MONEY |===============" + reset);
        System.out.println(green + "--> 1. CHECKING ACCOUNT → SAVING ACCOUNT");
        System.out.println("--> 2. SAVING ACCOUNT → CHECKING ACCOUNT" + reset);
        System.out.print("| Choose option (1 or 2): ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Account fromAccount = null, toAccount = null;

        // Find the Checking and Saving accounts
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] instanceof CheckingAccount) {
                if (choice == 1) fromAccount = accounts[i];  // Transfer from Checking
                if (choice == 2) toAccount = accounts[i];    // Receive in Checking
            } else if (accounts[i] instanceof SavingAccount) {
                if (choice == 1) toAccount = accounts[i];    // Receive in Saving
                if (choice == 2) fromAccount = accounts[i];  // Transfer from Saving
            }
        }

        if (fromAccount == null || toAccount == null) {
            System.out.println(red + "Error: Required accounts not found!" + reset);
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (fromAccount.balance >= amount) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            System.out.println(green + "\nTransfer Successful!" + reset);

            System.out.println(yellow + "===============| Transfer Info |===============" + reset);
            System.out.println("Transferred Amount : " + amount + "$");
            System.out.println("Transferred From : " + fromAccount.getAccountType() + " (Account No : " + fromAccount.accountNumber + ")");
            System.out.println("Transferred To : " + toAccount.getAccountType() + " (Account No : " + toAccount.accountNumber + ")");
            System.out.println("Remaining Balance in " + fromAccount.getAccountType() + " : $" + fromAccount.balance);
        } else {
            System.out.println(red + "Insufficient funds in " + fromAccount.getAccountType() + "!" + reset);
        }
    }

    public static void displayAccountInformation(Scanner scanner){
        if (accountCount == 0) {
            System.out.println(red + "No accounts found! Please create an account first." + reset);
            return;
        }

        System.out.println(yellow + "===============| ALL ACCOUNT INFORMATION |===============" + reset);
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccountInfo(); // Display each account's details
        }
    }

    public static void deleteAccount(Scanner scanner) {
        if (accountCount == 0) {
            System.out.println(red + "No accounts available to delete!" + reset);
            return;
        }

        System.out.println(yellow + "===============| DELETE ACCOUNT |===============" + reset);

        System.out.println(green + "Existing Accounts:" + reset);
        for (int i = 0; i < accountCount; i++) {
            System.out.println((i + 1) + ". " + accounts[i].getAccountType() + " (Account No: " + accounts[i].accountNumber + ")");
        }

        System.out.print("Enter the number of the account to delete (1-" + accountCount + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > accountCount) {
            System.out.println(red + "Invalid choice! No account deleted." + reset);
            return;
        }

        // Confirm deletion
        System.out.print("Are you sure you want to delete this account? (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            for (int i = choice - 1; i < accountCount - 1; i++) {
                accounts[i] = accounts[i + 1];
            }
            accounts[--accountCount] = null;
            System.out.println(green + "Account deleted successfully!" + reset);
        } else {
            System.out.println(yellow + "Account deletion canceled." + reset);
        }
    }

}
