package ClaimManagementSystem.UI.Customer;

import ClaimManagementSystem.Utility.ClaimSystem;
import ClaimManagementSystem.UI.HomePage;

import java.util.Scanner;

public class CustomerPage {
    public static void run() {
        displayOptions();
    }

    private static void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("1. View customers");
        System.out.println("1. Add a customer");
        System.out.println("2. Delete a customer");
        System.out.println("0. Go back");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                ClaimSystem.displayCustomers();
                displayOptions();
                break;
            case "2":
                AddCustomer.run();
                break;
            case "3":
                DeleteCustomer.run();
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
