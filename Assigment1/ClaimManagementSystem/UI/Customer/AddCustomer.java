package ClaimManagementSystem.UI.Customer;

import ClaimManagementSystem.Utility.DataManager;
import ClaimManagementSystem.Model.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @author Nguyen The Vinh - s3979366
 */
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
        if (role.equals("D")) {
            Dependant dependant = new Dependant(id, name);
            DataManager.getCustomers().add(dependant);
            getPolicyHolder(scanner).addDependant(dependant);
        }
        else {
            PolicyHolder policyHolder = new PolicyHolder(id, name);
            policyHolder.setDependantList(getDependants(scanner));
            DataManager.getCustomers().add(policyHolder);
        }

        DataManager.overWriteCustomer();

        exit();
    }

    private static String getCustomerRole(Scanner scanner) {
        while (true) {
            System.out.println("Is this customer a dependant or a policy holder? ('q' to exit)");
            System.out.println("1. Dependant");
            System.out.println("2. Policy Holder");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("q")) {
                exit();
                break;
            }
            if (id.equalsIgnoreCase("1")) return "D";
            if (id.equalsIgnoreCase("2")) return "PH";
            System.out.println("Invalid choice. Please select a valid option.");
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

    private static PolicyHolder getPolicyHolder(Scanner scanner) {
        PolicyHolder policyHolder;
        while (true) {
            System.out.println("Enter policy holder id: ");
            String id = scanner.nextLine();

            if (!id.isEmpty()) {
                try {
                    policyHolder = (PolicyHolder) DataManager.getCustomer(id); {
                        if (policyHolder == null) System.out.println("There is no customer with this id");
                        else return policyHolder;
                    }
                } catch (ClassCastException e) {
                    System.out.println("This is not a policy holder!");
                }
            }
            else System.out.println("Customer name cannot be null");
        }
    }

    private static List<Dependant> getDependants(Scanner scanner) {
        List<Dependant> dependants = new ArrayList<>();
        while (true) {
            System.out.println("Enter dependant id:  ('q' to exit)");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("q")) {
                break;
            }
            if (!id.isEmpty()) {
                try {
                    Dependant customer = (Dependant) DataManager.getCustomer(id); {
                        if (customer == null) System.out.println("There is no customer with this id");
                        else dependants.add( customer);
                    }
                } catch (ClassCastException e) {
                    System.out.println("This is not a dependant!");
                }
            }
            else System.out.println("Customer name cannot be null");
        }
        return dependants;
    }

}
