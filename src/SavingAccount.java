public class SavingAccount extends Account {

    static final String red = "\u001b[31m";
    static final String green = "\u001b[32m";
    static final String yellow = "\u001b[33m";
    static final String reset = "\u001b[0m";

    double interestRate = 0.05;


    public SavingAccount(int accountNumber, String userName, String dateOfBirth, String gender, String phoneNumber, double balance) {
        super(accountNumber, userName, dateOfBirth, gender, phoneNumber, balance);
    }

    @Override
    void deposit(double amount) {
        balance += amount;
        System.out.println("Received : " + amount + "$");
        System.out.println("Total Amount : " + balance + "$");
    }

    @Override
    void withdraw(double amount) {
        if (amount <= balance * 0.8) {  // Can only withdraw up to 80% of balance
            balance -= amount;
            System.out.println("Withdrew : " + amount + "$");
            System.out.println("Total Balance : " + balance + "$");
        } else {
            System.out.println("Cannot withdraw more than 80% of your balance.");
        }
    }

    @Override
    void transfer(double amount, Account targetAccount) {
        if (amount <= balance) {
            balance -= amount;
            targetAccount.deposit(amount);
            System.out.println("Transferred: $" + amount);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    @Override
    public void displayAccountInfo() {
//        System.out.println("=====================================");
        System.out.println("Account Type: " + getAccountType());
        System.out.println("Account Number: " + accountNumber);
        System.out.println("User Name: " + userName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.printf("Balance: %.2f$\n", balance);
        System.out.println("=====================================");
    }
}
