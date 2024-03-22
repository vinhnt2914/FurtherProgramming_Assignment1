package ClaimManagementSystem.UI.Card;

import ClaimManagementSystem.DataManager;
import ClaimManagementSystem.Model.Customer;
import ClaimManagementSystem.Model.Dependant;
import ClaimManagementSystem.Model.InsuranceCard;
import ClaimManagementSystem.Model.PolicyHolder;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddCard {
    public static void run() {

    }

    private static void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        String cardNumber = getCardNumber(scanner);
        Customer cardHolder = getCardHolder(scanner);
        PolicyHolder policyOwner = getPolicyOwner(scanner);
        LocalDate expirationDate = getExpirationDate(scanner);
        InsuranceCard card = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
        
        DataManager.getInsuranceCards().put(cardNumber, card);
        DataManager.writeInsuranceCard(card);
    }

    private static String getCardNumber(Scanner scanner) {
        String cardNumber;
        while (true) {
            System.out.println("Enter card number: ");
            cardNumber = scanner.nextLine();
            if (cardNumber.matches("^\\d{10}$")) {
                if (DataManager.getInsuranceCards().containsKey(cardNumber)) {
                    System.out.println("There is already an insurance card with this number!");
                } else return cardNumber;

            } else {
                System.out.println("Number is invalid. Must be a 10 digits number.");
            }
        }
    }

    private static Customer getCardHolder(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the card holder's id:");
            String id = scanner.nextLine();

            if (id.matches("^c-\\d{7}$")) {
                Customer customer = DataManager.getCustomer(id);
                if (customer == null) {
                    System.out.println("There is no customer with this id!");
                }
                // If the new insured person doesn't have a card. They are not eligible
                else if (customer.getInsuranceCard() != null) {
                    System.out.println("This customer already had a card. A customer can only have one card!");
                } else return customer;
            } else System.out.println("Wrong id format. Must be c-number (7 digits)");
        }
    }

    private static PolicyHolder getPolicyOwner(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the policy owner's id:");
            String id = scanner.nextLine();

            if (id.matches("^c-\\d{7}$")) {
                Customer customer = DataManager.getCustomer(id);
                if (customer == null) {
                    System.out.println("There is no customer with this id!");
                }
                // Check is user entered id of a dependant
                else if (customer instanceof Dependant) {
                    System.out.println("Policy owner must be a policy holder");
                }
                // If the new insured person doesn't have a card. They are not eligible
                else return (PolicyHolder) customer;
            } else System.out.println("Wrong id format. Must be c-number (7 digits)");
        }
    }

    private static LocalDate getExpirationDate(Scanner scanner) {
        LocalDate date;
        while (true) {
            System.out.println("Enter expiration date (yyyy-MM-dd): ");
            try {
                date = LocalDate.parse(scanner.nextLine());
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
    }


}
