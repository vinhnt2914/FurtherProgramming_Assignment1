package ClaimManagementSystem.UI.Customer;

import ClaimManagementSystem.DataManager;
import ClaimManagementSystem.Model.Claim;
import ClaimManagementSystem.Model.Customer;
import ClaimManagementSystem.Model.InsuranceCard;
import ClaimManagementSystem.Model.PolicyHolder;

import java.util.List;
import java.util.Scanner;

public class DeleteCustomer {
    public static void run() {
        displayOptions();
    }

    private static void exit() {
        CustomerPage.run();
    }

    private static void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        Customer customer = getCustomerId(scanner);

        // If the customer have cards or claims, delete everything related to them.
        // Get all the cards and claims
        assert customer != null;
        InsuranceCard card = customer.getInsuranceCard();
        List<Claim> claims = customer.getClaims();

        // Delete cards and claims from system and database
        if (card != null) DataManager.getInsuranceCards().remove(card.getCardNumber());
        if (!claims.isEmpty()) {
            claims.forEach(claim -> {
                DataManager.getClaims().remove(claim.getId());
            });
        }

        // If the deleted customer is a dependant then delete them from the policyholder's list of dependants
        for (Customer c : DataManager.getCustomers()) {
            if (c instanceof PolicyHolder) {
                ((PolicyHolder) c).getDependantList().remove(customer);
            }
        }

        // Remove the customer after all related cards and claims are removed
        DataManager.getCustomers().remove(customer);

        // Overwrite all data
        DataManager.overWriteCustomer();
        DataManager.overWriteInsuranceCards();
        DataManager.overWriteClaim();
    }

    private static Customer getCustomerId(Scanner scanner) {
        while (true) {
            System.out.println("Enter the customer id that you want to delete:   ('q' to exit)");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("q")) {
                exit();
                break;
            }
            Customer customer = DataManager.getCustomer(id);
            if (customer != null) return customer;
            else System.out.println("There is no customer with this id");
        }
        return null;
    }
}
