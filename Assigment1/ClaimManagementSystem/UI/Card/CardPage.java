package ClaimManagementSystem.UI.Card;

import ClaimManagementSystem.Utility.ClaimSystem;

import java.util.Scanner;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class CardPage {
    public static void run() {
        displayOptions();
    }
    private static void displayOptions() {
        System.out.println("What do you want to do with insurance cards?");
        System.out.println("1. Add a card");
        System.out.println("2. View insurance cards");
        System.out.println("0. Go back");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                AddCard.run();
                break;
            case "2":
                ClaimSystem.displayCards();
                displayOptions();
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
