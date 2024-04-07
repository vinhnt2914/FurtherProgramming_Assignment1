package ClaimManagementSystem.UI.Claim;

import ClaimManagementSystem.Utility.DataManager;
import ClaimManagementSystem.Model.Claim;
import ClaimManagementSystem.Model.Customer;
import ClaimManagementSystem.UI.HomePage;
import ClaimManagementSystem.Utility.ClaimService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Nguyen The Vinh - s3979366
 */
public class UpdateClaim {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Which claim do you want to update?");
            System.out.println("Enter claim id: ('q' to exit)");
            String id = scanner.nextLine();
            // Return to homepage
            if (id.equals("q")) {
                HomePage.run();
                break;
            }

            // Get claim from the system.
            Claim claim = DataManager.getClaim(id);
            if (claim == null) {
                System.out.println("This claim doesn't exist");
            } else {
                displayOptions(scanner, claim);
            }
        }
    }

    private static void displayOptions(Scanner scanner, Claim claim) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Select what you want to change in this claim:");
            System.out.println("1. Claim Date");
            System.out.println("2. Insured Person");
            System.out.println("3. Exam Date");
            System.out.println("4. Claim Amount");
            System.out.println("5. Claim Status");
            System.out.println("6. Bank Name");
            System.out.println("7. Receiver Name");
            System.out.println("8. Bank Number");
            System.out.println("9. Document");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    new ClaimService().updateClaimDate(claim, getClaimDate(scanner));
                    break;
                case "2":
                    new ClaimService().updateInsuredPerson(claim, getInsuredPerson(scanner));
                    break;
                case "3":
                    new ClaimService().updateExamDate(claim, getExamDate(scanner));
                    break;
                case "4":
                    new ClaimService().updateClaimAmount(claim, getClaimAmount(scanner));
                    break;
                case "5":
                    new ClaimService().updateClaimStatus(claim, getClaimStatus(scanner));
                    break;
                case "6":
                    new ClaimService().updateBankName(claim, getBankName(scanner));
                    break;
                case "7":
                    new ClaimService().updateReceiver(claim, getReceiverName(scanner));
                    break;
                case "8":
                    new ClaimService().updateBankNumber(claim, getBankNumber(scanner));
                    break;
                case "9":
                    new ClaimService().updateDocuments(claim, getDocuments(scanner));
                    break;
                case "0":
                    exit = true; // Set exit to true to exit the loop
                    HomePage.run();
                    break;
                default:
                    System.out.println();
                    System.out.println("⚠️ Invalid choice. Please select a valid option.");
                    break;
            }

            DataManager.overWriteClaim();
            DataManager.overWriteCustomer();
        }
    }

    private static LocalDate getClaimDate(Scanner scanner) {
        LocalDate date;
        while (true) {
            System.out.println("Enter claim date (yyyy-MM-dd): ");
            try {
                date = LocalDate.parse(scanner.nextLine());
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
    }

    private static Customer getInsuredPerson(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the customer id:");
            String id = scanner.nextLine();

            if (id.matches("^c-\\d{7}$")) {
                Customer customer = DataManager.getCustomer(id);
                if (customer == null) {
                    System.out.println("There is no customer with this id!");
                }
                // If the new insured person doesn't have a card. They are not eligible
                else if (customer.getInsuranceCard() == null) {
                    System.out.println("This customer doesn't have an insurance card. They are not eligible for this claim!");
                } else return customer;
            } else System.out.println("Wrong id format. Must be c-number (7 digits)");
        }
    }

    private static LocalDate getExamDate(Scanner scanner) {
        LocalDate date;
        while (true) {
            System.out.println("Enter exam date (yyyy-MM-dd): ");
            try {
                date = LocalDate.parse(scanner.nextLine());
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
    }

    private static double getClaimAmount(Scanner scanner) {
        while (true) {
            System.out.println("Enter claim amount:");
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static Claim.ClaimStatus getClaimStatus(Scanner scanner) {
        while (true) {
            System.out.println("Enter claim status (New/Processing/Done):");
            String userInput = scanner.nextLine().trim().toLowerCase(); // Convert input to lowercase and trim whitespace
            switch (userInput) {
                case "new":
                    return Claim.ClaimStatus.New;
                case "processing":
                    return Claim.ClaimStatus.Processing;
                case "done":
                    return Claim.ClaimStatus.Done;
                default:
                    System.out.println("Invalid status. Please enter New, Processing, or Done.");
            }
        }
    }

    private static String getBankName(Scanner scanner) {
        while (true) {
            System.out.println("Enter receiver bank name:");
            String bankName = scanner.nextLine();
            if (!bankName.isEmpty()) {
                return bankName;
            }
        }
    }

    private static String getReceiverName(Scanner scanner) {
        while (true) {
            System.out.println("Enter receiver name:");
            String receiverName = scanner.nextLine();
            if (!receiverName.isEmpty()) {
                return receiverName;
            }
        }
    }

    private static String getBankNumber(Scanner scanner) {
        while (true) {
            System.out.println("Enter credit card number:");
            String bankNumber = scanner.nextLine();
            if (!bankNumber.isEmpty()) {
                return bankNumber;
            }
        }
    }

    private static List<String> getDocuments(Scanner scanner) {
        List<String> documents = new ArrayList<>();
        while (true) {
            System.out.println("Enter document: ('q' to exit)");
            String document = scanner.nextLine();
            if (document.equals("q")) return documents;
            if (document.matches("^f-\\d{10}_\\d{10}_\\w+\\.pdf$")) {
                documents.add(document);
            } else {
                System.out.println("Invalid document format. Please follow the format: 'ClaimID_CardNumber_documentName.pdf'");
            }
        }
    }



}
