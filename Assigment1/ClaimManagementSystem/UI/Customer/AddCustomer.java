package ClaimManagementSystem.UI.Customer;

import ClaimManagementSystem.DataManager;
import ClaimManagementSystem.Model.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddCustomer {
    public static void run() {
        displayOptions();
    }

    private static void exit() {
        CustomerPage.run();
    }

    private static void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        String role = getCustomerRole(scanner);
        String id = getCustomerId(scanner);
        String name = getCustomerName(scanner);

        // Decide what type of customer to create
        Customer customer;
        if (role.equals("D")) customer = new Dependant(id, name);
        else customer = new PolicyHolder(id, name);

        // Execute getInsuranceCard and set the customer's card
        InsuranceCard card = getInsuranceCard(scanner, customer);
        customer.setInsuranceCard(card);
        DataManager.getCustomers().add(customer);
        DataManager.overWriteCustomer();


        // If the customer has no card, then don't add to a database
        if (card != null) {
            DataManager.getInsuranceCards().put(card.getCardNumber(), card);
            DataManager.writeInsuranceCard(card);
        }


    }

    private static String getCustomerRole(Scanner scanner) {
        while (true) {
            System.out.println("Is this customer a dependant or a policy holder? ('q' to exit)");
            System.out.println("1. Dependant ");
            System.out.println("2. Policy Holder");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("q")) {
                exit();
                break;
            }
            if (id.equalsIgnoreCase("1")) return "D";
            if (id.equalsIgnoreCase("2")) return "PH";
            System.out.println("⚠️ Invalid choice. Please select a valid option.");
        }
        return null;
    }

    /**
     * <p>
     *     Get customer from input
     * </p>
     * @param scanner Scanner object
     * @return customer id
     * */
    private static String getCustomerId(Scanner scanner) {
        while (true) {
            System.out.println("Enter customer id: ");
            String id = scanner.nextLine();
            if (id.matches("^c-\\d{7}$")) {
                if (DataManager.getCustomer(id) == null)
                    return id;
                else System.out.println("This id is already taken");
            }
            else System.out.println("Invalid id format, must be c-number (7 digits)!");
        }
    }

    private static String getCustomerName(Scanner scanner) {
        while (true) {
            System.out.println("Enter customer name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) return name;
            else System.out.println("Customer name cannot be null");
        }
    }


    /**
     * <p>
     *     A customer can be created with id and name only.
     *     Inputting the card id, will invoke other functions
     *     to create a new card for this customer. A card alone
     *     must have a holder and policy owner so it's a must to
     *     create a new card.
     * </p>
     * */
    private static InsuranceCard getInsuranceCard(Scanner scanner, Customer customer) {
        System.out.println("Does this customer has an insurance card? (y/n)");
        String ans = scanner.nextLine();
        if (ans.equalsIgnoreCase("y")) {
            return createCardForCustomer(scanner, customer);
        } else return null;
    }

    private static InsuranceCard createCardForCustomer(Scanner scanner, Customer customer) {
        String cardNumber = getCardNumber(scanner);
        String policyOwner = getPolicyOwner(scanner);
        LocalDate expirationDate = getExpirationDate(scanner);
        return new InsuranceCard(cardNumber, customer, policyOwner, expirationDate);
    }

    private static String getCardNumber(Scanner scanner) {
        while (true) {
            System.out.println("Enter card number: ");
            String cardNumber = scanner.nextLine();
            if (cardNumber.matches("\\d{10}")) return cardNumber;
            else System.out.println("Card number name cannot be null");
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
