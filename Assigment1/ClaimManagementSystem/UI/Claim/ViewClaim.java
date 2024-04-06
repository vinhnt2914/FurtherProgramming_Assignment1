package ClaimManagementSystem.UI.Claim;
import ClaimManagementSystem.Utility.ClaimSystem;
import ClaimManagementSystem.Utility.DataManager;

import java.util.Scanner;

public class ViewClaim {
    public static void run() {
        displayOptions();
    }

    private static void displayOptions() {
        System.out.println("What do you want to view");
        System.out.println("1. View a claim");
        System.out.println("2. View all claim");
        System.out.println("0. Go back");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                viewOneClaim(scanner);
                break;
            case "2":
                viewAllClaims();
                break;
            case "0":
                ClaimPage.run();
                break;

            default:
                System.out.println();
                System.out.println("⚠️ Invalid choice. Please select a valid option.");
                displayOptions();
                break;
        }
    }

    private static void viewAllClaims() {
        ClaimSystem.displayClaims();
    }

    private static void viewOneClaim(Scanner scanner) {
        String id = getId(scanner);
        ClaimSystem.displayOneClaim(id);
    }

    private static String getId(Scanner scanner) {
        String id;
        while (true) {
            System.out.println("Enter claim id: ");
            id = scanner.nextLine();
            if (id.matches("^f-\\d{10}$")) {
                if (!DataManager.getClaims().containsKey(id)) {
                    System.out.println("There is no claim with this id");
                } else return id;

            } else {
                System.out.println("ID is invalid. Please enter a valid claim ID.");
            }
        }
    }
}
