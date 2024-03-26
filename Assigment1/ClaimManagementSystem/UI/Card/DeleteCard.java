package ClaimManagementSystem.UI.Card;

import ClaimManagementSystem.ClaimSystem;
import ClaimManagementSystem.DataManager;
import ClaimManagementSystem.Model.Claim;
import ClaimManagementSystem.Model.Customer;
import ClaimManagementSystem.Model.InsuranceCard;
import ClaimManagementSystem.Utility.ClaimService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteCard {
    public static void run() {

    }

    private static void displayOptions() {
        Scanner scanner = new Scanner(System.in);
    }

    /**
     * <p>
     *     Deleting a card should also set the customer's (owner) card to null.
     *     And the claim with the deleted insurance card should also be removed.
     * </p>
     * */
    private static void deleteCard(InsuranceCard insuranceCard, Scanner scanner) {
        List<Claim> claims = getClaimOfCard(insuranceCard);
        // If there is a claim related to this card
        if (!claims.isEmpty()) {
            System.out.println("There are claims related to this card.");
            System.out.println("Deleting this card means that all related claims will be remove as well.");
            String ans = getConfirmation(scanner);
            if (ans.equalsIgnoreCase("y")) {
                claims.forEach(claim -> new ClaimService().delete(claim));
            }

            // Get and remove the card from the cardholder
            Customer customer = insuranceCard.getCardHolder();
            customer.removeCard(insuranceCard);

            // Remove card from the system
            DataManager.getInsuranceCards().remove(insuranceCard);

            // Overwrite data
            DataManager.overWriteCustomer();
            DataManager.overWriteClaim();
            DataManager.overWriteInsuranceCards();
        }
    }

    private static String getConfirmation(Scanner scanner) {
        while (true) {
            System.out.println("Are you sure about this (y/n)?");
            String ans = scanner.nextLine();
            if (ans.isEmpty() || !ans.matches("[ynYN]")) {
                System.out.println("Invalid answer!");
            } else return ans;
        }
    }

    private static List<Claim> getClaimOfCard(InsuranceCard insuranceCard) {
        List<Claim> claims = new ArrayList<>();
        for (Claim claim : DataManager.getClaims().values()) {
            if (claim.getCardNumber().equals(insuranceCard.getCardNumber())) {
                claims.add(claim);
            }
        }
        return claims;
    }

    private static InsuranceCard getInsuranceCard(Scanner scanner) {
        while (true) {
            System.out.println("Enter the card number you want to delete:");
            String cardNumber = scanner.nextLine();
            if (cardNumber.matches("^\\d{10}$")) {
                return DataManager.getInsuranceCard(cardNumber);
            } else {
                System.out.println("Invalid card number format. Please enter a 10-digit card number.");
            }
        }
    }
}
