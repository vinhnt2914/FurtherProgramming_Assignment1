package ClaimManagementSystem.Utility;

import ClaimManagementSystem.Model.*;
import ClaimManagementSystem.UI.HomePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Nguyen The Vinh - s3979366
 */
public class ClaimSystem {

    private static final ClaimService claimService = new ClaimService();

    public static void run() {
        HomePage.run();
    }

    public static void displayCustomers() {
        System.out.println("CUSTOMER DETAILS");
        System.out.println();
        System.out.printf("%-20s %-30s %-20s %-20s %-20s\n", "ID", "Name", "Insurance Card", "Claims", "Dependant");

        List<Customer> sortedCustomer = new ArrayList<>(DataManager.getCustomers());
        sortedCustomer.sort(Comparator.comparing(Customer::getId));

        for (Customer customer : sortedCustomer) {
            System.out.printf("%-20s %-30s %-20s %-20s %-20s\n",
                    customer.getId(),
                    customer.getName(),
                    customer.getInsuranceCard() == null ? "None" : customer.getInsuranceCard().getCardNumber(),
                    customer.getClaims().isEmpty() ? "None" : customer.getClaims().size(),
                    customer instanceof PolicyHolder ? ((PolicyHolder) customer).getDependantsIDS() : "Not a policy holder");
        }
        System.out.println();
    }

    public static void displayCards() {
        System.out.println("CARD DETAILS");
        System.out.println();
        System.out.printf("%-20s %-20s %-20s %-20s\n", "Number", "Card Holder", "Policy Owner", "Expiration Date");

        for (InsuranceCard card : DataManager.getInsuranceCards().values()) {
            System.out.printf("%-20s %-20s %-20s %-20s\n",
                    card.getCardNumber(),
                    card.getCardHolder().getId(),
                    card.getPolicyOwner(),
                    card.getExpirationDate());

        }
        System.out.println();
    }

    public static void displayClaims() {
        System.out.printf("%-15s %-15s %-20s %-15s %-15s %-10s %-15s %-10s %-15s %-20s %-15s\n",
                "ID", "Claim Date", "Insured Person", "Card Number",
                "Exam Date", "Documents", "Claim Amount", "Status",
                "Bank", "Receiver", "Bank Number");

        for (Claim claim : claimService.getAll()) {
            System.out.printf("%-15s %-15s %-20s %-15s %-15s %-10s %-15s %-10s %-15s %-20s %-15s\n",
                    claim.getId(), claim.getClaimDate(), claim.getInsuredPerson().getName(), claim.getCardNumber(),
                    claim.getExamDate(), claim.getDocuments().size(), claim.getClaimAmount(), claim.getStatus(),
                    claim.getBankName(), claim.getReceiverName(), claim.getBankNumber());
        }
        System.out.println();
    }

    public static void displayOneClaim(String id) {
        Claim claim = claimService.getOne(id);
        System.out.println(claim);
//        System.out.printf("%-15s %-15s %-20s %-15s %-15s %-10s %-15s %-10s %-15s %-20s %-15s\n",
//                "ID", "Claim Date", "Insured Person", "Card Number",
//                "Exam Date", "Documents", "Claim Amount", "Status",
//                "Bank", "Receiver", "Bank Number");
//
//        System.out.printf("%-15s %-15s %-20s %-15s %-15s %-10s %-15s %-10s %-15s %-20s %-15s\n",
//                claim.getId(), claim.getClaimDate(), claim.getInsuredPerson().getName(), claim.getCardNumber(),
//                claim.getExamDate(), claim.getDocuments().size(), claim.getClaimAmount(), claim.getStatus(),
//                claim.getBankName(), claim.getReceiverName(), claim.getBankNumber());
//
//        System.out.println();
    }

}
