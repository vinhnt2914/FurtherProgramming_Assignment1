package ClaimManagementSystem.UI;

import ClaimManagementSystem.UI.Claim.AddClaim;
import ClaimManagementSystem.UI.Claim.ClaimPage;
import ClaimManagementSystem.UI.Customer.CustomerPage;

import java.util.Scanner;

public class HomePage {
    public static void run() {
        System.out.println("Welcome to the Claim Management System!");
        System.out.println("=======================================");
        displayOptions();
    }

    private static void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("1. Customers");
        System.out.println("2. Insurance Cards");
        System.out.println("3. Claim");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                CustomerPage.run();
                break;
            case "2":
                // Process insurance claims logic
                break;
            case "3":
                ClaimPage.run();
                break;
            case "0":
                System.out.println("Thank you for using the Claim Management System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println();
                System.out.println("⚠️ Invalid choice. Please select a valid option.");
                displayOptions();
                break;
        }
    }

}
