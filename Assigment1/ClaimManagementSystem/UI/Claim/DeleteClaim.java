package ClaimManagementSystem.UI.Claim;

import ClaimManagementSystem.Utility.DataManager;
import ClaimManagementSystem.Model.Claim;
import ClaimManagementSystem.Utility.ClaimService;

import java.util.Scanner;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class DeleteClaim {
    public static void run() {
        displayOptions();
    }

    private static void exit() {
        ClaimPage.run();
    }

    private static void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        Claim claim = getClaimId(scanner);
        if (claim != null) {
            new ClaimService().delete(claim);
        }
    }

    private static Claim getClaimId(Scanner scanner) {
        while (true) {
            System.out.println("Enter the claim id that you want to delete:   ('q' to exit)");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("q")) {
                exit();
                break;
            }

            if (id.matches("^f-\\d{10}$")) {
                Claim claim = DataManager.getClaim(id);
                if (claim != null) return claim;
                else System.out.println("There is no claim with this id");
            } else {
                System.out.println("Invalid claim id, must be in format: f-number (10 digits)");
            }
        }
        return null;
    }




}
