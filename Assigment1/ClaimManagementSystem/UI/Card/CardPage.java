package ClaimManagementSystem.UI.Card;

import ClaimManagementSystem.UI.Customer.AddCustomer;
import ClaimManagementSystem.UI.Customer.DeleteCustomer;
import ClaimManagementSystem.UI.HomePage;

import java.util.Scanner;

public class CardPage {
    public static void run() {

    }

    private static void displayOptions() {
        System.out.println("What do you want to do with insurance cards?");
        System.out.println("1. Add a card");
        System.out.println("2. Delete a card");
        System.out.println("3. Go back");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":

                break;
            case "2":

                break;
            case "3":

                break;
            default:
                System.out.println();
                System.out.println("⚠️ Invalid choice. Please select a valid option.");
                displayOptions();
                break;
        }
    }
}
