abstract class Account {
    abstract void deposit(double amount);
    abstract void withdraw(double amount);
    abstract void transfer (double amount, Account targetAccount);
    abstract void displayAccountInfo();

    int accountNumber;
    String userName;
    String dateOfBirth;
    String gender;
    String phoneNumber;
    double balance;

    public Account(int accountNumber, String userName, String dateOfBirth, String gender, String phoneNumber, double balance) {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public int getId() {
        return accountNumber;
    }
    public String getAccountType() {
        return this instanceof CheckingAccount ? "Checking Account" : "Saving Account";
    }



}
