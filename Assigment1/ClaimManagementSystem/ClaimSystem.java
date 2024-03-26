package ClaimManagementSystem;

import ClaimManagementSystem.Model.*;
import ClaimManagementSystem.UI.HomePage;

import java.util.ArrayList;
import java.util.List;

public class ClaimSystem {

    public static void run() {
        HomePage.run();
    }

    public static void displayCustomers() {
        System.out.println("CUSTOMER DETAILS");
        System.out.println();
        System.out.printf("%-20s %-30s %-20s %-20s %-20s\n", "ID", "Name", "Insurance Card", "Claims", "Dependant");

        for (Customer customer : DataManager.getCustomers()) {
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

        for (Claim claim : DataManager.getClaims().values()) {
            System.out.printf("%-15s %-15s %-20s %-15s %-15s %-10s %-15s %-10s %-15s %-20s %-15s\n",
                    claim.getId(), claim.getClaimDate(), claim.getInsuredPerson().getName(), claim.getCardNumber(),
                    claim.getExamDate(), claim.getDocuments().size(), claim.getClaimAmount(), claim.getStatus(),
                    claim.getBankName(), claim.getReceiverName(), claim.getBankNumber());
        }
        System.out.println();
    }

}
