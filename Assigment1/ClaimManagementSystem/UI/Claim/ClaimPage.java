package ClaimManagementSystem.UI.Claim;

import ClaimManagementSystem.Utility.ClaimSystem;
import ClaimManagementSystem.UI.HomePage;

import java.util.Scanner;

public class ClaimPage {

    public static void run() {
        displayOptions();
    }

    private static void displayOptions() {
        System.out.println("What do you want to do with claims");
        System.out.println("1. View Claim");
        System.out.println("2. Add Claim");
        System.out.println("3. Update Claim");
        System.out.println("4. Delete Claim");
        System.out.println("0. Go Back");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                ViewClaim.run();
                displayOptions();
                break;
            case "2":
                AddClaim.run();
                break;
            case "3":
                UpdateClaim.run();
                break;
            case "4":
                DeleteClaim.run();
                break;
            case "0":
                HomePage.run();
                break;
            default:
                System.out.println();
                System.out.println("⚠️ Invalid choice. Please select a valid option.");
                displayOptions();
                break;
        }
    }


}
