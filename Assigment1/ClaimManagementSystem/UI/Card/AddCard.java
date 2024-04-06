package ClaimManagementSystem.UI.Card;

import ClaimManagementSystem.Utility.DataManager;
import ClaimManagementSystem.Model.Customer;
import ClaimManagementSystem.Model.InsuranceCard;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class AddCard {
    public static void run() {
        displayOptions();
    }

    private static void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        String cardNumber = getCardNumber(scanner);
        Customer cardHolder = getCardHolder(scanner);
        String policyOwner = getPolicyOwner(scanner);
        LocalDate expirationDate = getExpirationDate(scanner);
        InsuranceCard card = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
        
        addCard(card);
    }

    private static void addCard(InsuranceCard card) {
        DataManager.getInsuranceCards().put(card.getCardNumber(), card);
        Customer customer = DataManager.getCustomer(card.getCardHolder().getId());
        if (customer != null) {
            customer.setInsuranceCard(card);
            DataManager.writeInsuranceCard(card);
            DataManager.overWriteCustomer();
        }
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

    private static String getPolicyOwner(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the policy owner:");
            String policyOwner = scanner.nextLine();
            if (policyOwner.isEmpty()) {
                System.out.println("Policy owner cannot be empty!");
            } else return policyOwner;
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
