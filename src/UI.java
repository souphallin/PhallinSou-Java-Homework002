import java.util.Scanner;

public class UI {
    static final String red = "\u001b[31m";
    static final String green = "\u001b[32m";
    static final String yellow = "\u001b[33m";
    static final String reset = "\u001b[0m";

    public void display(){

        SavingAccount savingAccount =new SavingAccount();
        CheckingAccount checkingAccount = new CheckingAccount();

        Scanner scanner = new Scanner(System.in);
        while (true) {
//            System.out.println(yellow + "===============| --------- |===============" + reset);
            System.out.println(green + "--> 1. CHECKING ACCOUNT");
            System.out.println("--> 2. SAVING ACCOUNT");
            System.out.println("--> 3. BACK");
            System.out.print("| CHOOSE OPTION 1-3: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 :
                    savingAccount.saving(scanner);
                    break;
                case 2 :
                    checkingAccount.checking(scanner);
                    break;
                case 3 :
                    break;
            }
        }
    }
    public void displayTransferMoney(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(green + "--> 1. CHECKING ACCOUNT --> SAVING ACCOUNT");
            System.out.println("--> 2. SAVING ACCOUNT --> CHECKING ACCOUNT");
            System.out.println("--> 3. BACK");
            System.out.print("| CHOOSE OPTION 1-3: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
            }
        }
    }
}
