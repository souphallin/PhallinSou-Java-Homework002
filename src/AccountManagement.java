import java.util.Scanner;

public class AccountManagement extends UI{
    static final String red = "\u001b[31m";
    static final String green = "\u001b[32m";
    static final String yellow = "\u001b[33m";
    static final String reset = "\u001b[0m";

    public static void createAccount(Scanner scanner){
        System.out.println(yellow + "===============| CREATING ACCOUNT |===============" + reset);
        UI ui = new UI();
        ui.display();
    }
    public static void depositMoney(Scanner scanner){
        System.out.println(yellow + "===============| DEPOSIT MONEY |===============" + reset);
        UI ui = new UI();
        ui.display();
    }
    public static void withdrawMoney(Scanner scanner){
        System.out.println(yellow + "===============| WITHDRAW MONEY |===============" + reset);
        UI ui = new UI();
        ui.display();
    }
    public static void transferMoney(Scanner scanner){
        System.out.println(yellow + "===============| TRANSFER MONEY |===============" + reset);
        UI ui = new UI();
        ui.displayTransferMoney();
    }
    public static void displayAccountInformation(Scanner scanner){

    }
    public static void deleteAccount(Scanner scanner){
        System.out.println(yellow + "===============| WITHDRAW MONEY |===============" + reset);
        UI ui = new UI();
        ui.display();
    }
}
