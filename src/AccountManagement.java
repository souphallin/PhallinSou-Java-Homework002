
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

    public static void createAccount(Scanner scanner) {
        System.out.println(yellow + "===============| CREATING ACCOUNT |===============" + reset);

        if (accountCount >= accounts.length) {
            System.out.println(red + "Account limit reached!" + reset);
            return;
        }

        int type;
        while (true) {
            System.out.println("1. CHECKING ACCOUNT");
            System.out.println("2. SAVING ACCOUNT");
            System.out.print("Which account do you want to create? (1 - 2) : ");
            type = scanner.nextInt();
            scanner.nextLine();
            if (type == 1 || type == 2) break;
            System.out.println(red + "Invalid choice! Please enter 1 or 2." + reset);
        }

        // Generate a random 6-digit account number
        Random random = new Random();
        int accountNumber = 100000 + random.nextInt(900000);

        // Validate Username (only letters, not empty)
        String userName;
        while (true) {
            System.out.print("Enter Username: ");
            userName = scanner.nextLine().trim();
            if (!userName.matches("[a-zA-Z ]+") || userName.isEmpty()) {
                System.out.println(red + "Invalid Username! Must contain only letters and cannot be empty." + reset);
            } else {
                break;
            }
        }

        // Validate Date of Birth (Cannot be empty)
        String dateOfBirth;
        while (true) {
            System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
            dateOfBirth = scanner.nextLine().trim();
            if (!isValidDate(dateOfBirth)) {
                System.out.println(red + "Invalid Date of Birth! Please enter a valid date (DD/MM/YYYY)." + reset);
            } else {
                break;
            }
        }

        // Validate Gender (Cannot be empty)
        String gender;
        while (true) {
            System.out.print("Enter Gender: ");
            gender = scanner.nextLine().trim();
            if (gender.isEmpty()) {
                System.out.println(red + "Gender cannot be empty!" + reset);
            } else {
                break;
            }
        }

        // Validate Phone Number (only digits, not empty)
        String phoneNumber;
        while (true) {
            System.out.print("Enter Phone Number: ");
            phoneNumber = scanner.nextLine().trim();
            if (!phoneNumber.matches("\\d+") || phoneNumber.isEmpty()) {
                System.out.println(red + "Invalid Phone Number! Must contain only numbers and cannot be empty." + reset);
            } else {
                break;
            }
        }

        double balance = 0.0;

        Account newAccount = (type == 1)
                ? new CheckingAccount(accountNumber, userName, dateOfBirth, gender, phoneNumber, balance)
                : new SavingAccount(accountNumber, userName, dateOfBirth, gender, phoneNumber, balance);

        accounts[accountCount++] = newAccount;
        currentAccount = newAccount;

        System.out.println(green + "Account created successfully! Your account number is: " + accountNumber + reset);
    }

    public static boolean isValidDate(String dateOfBirth) {
        // Regular expression for DD/MM/YYYY format
        String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

        // Check if the input matches the pattern
        if (!dateOfBirth.matches(datePattern)) {
            return false; // Invalid format
        }

        // Split into day, month, and year
        String[] parts = dateOfBirth.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Check if the year is valid (adjust range if needed)
        if (year < 1900 || year > 2025) {
            return false;
        }

        // Days in each month (default for a non-leap year)
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Leap year check
        if (isLeapYear(year)) {
            daysInMonth[1] = 29; // February has 29 days in a leap year
        }

        // Validate day against the month's days limit
        return day > 0 && day <= daysInMonth[month - 1];
    }

    // Leap Year Checker
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
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
            accounts[i].displayAccountInfo();
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
