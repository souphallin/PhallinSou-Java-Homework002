import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends AccountManagement{
    public static void main(String[] args) {
        final String red = "\u001b[31m";
        final String green = "\u001b[32m";
        final String yellow = "\u001b[33m";
        final String reset = "\u001b[0m";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(yellow + "===============| ONLINE BANKING SYSTEM |===============" + reset);
            System.out.println(green + "--> 1. CREATE ACCOUNT");
            System.out.println("--> 2. DEPOSIT MONEY");
            System.out.println("--> 3. WITHDRAW MONEY");
            System.out.println("--> 4. TRANSFER MONEY");
            System.out.println("--> 5. DISPLAY ACCOUNT INFORMATION");
            System.out.println("--> 6. DELETE ACCOUNT");
            System.out.println("--> 7. EXIT" + reset);
            System.out.print("| CHOOSE OPTION 1-7: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 :
                    createAccount(scanner);
                    break;
                case 2 :
                    depositMoney(scanner);
                    break;
                case 3 :
                    withdrawMoney(scanner);
                    break;
                case 4 :
                    transferMoney(scanner);
                    break;
                case 5 :
                    displayAccountInformation(scanner);
                    break;
                case 6 :
                    deleteAccount(scanner);
                    break;
                case 7 :
                    System.out.println("======================================");
                    System.out.println(red + " | Closed the program!! Thank You.. |" + reset);
                    System.out.println("======================================");
                    return;
                default:
                    System.out.println("======================================");
                    System.out.println(red + " INVALID CHOOSE, PLEASE CHOOSE 1-6 !!" + reset);
                    System.out.println("======================================");
            }
        }
    }
}