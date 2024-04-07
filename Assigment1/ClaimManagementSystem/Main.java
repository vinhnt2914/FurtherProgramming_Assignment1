package ClaimManagementSystem;

import ClaimManagementSystem.Utility.ClaimSystem;
import ClaimManagementSystem.Utility.DataManager;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class Main {
    public static void main(String[] args) {
        // Load data
        DataManager.readCustomer();
        DataManager.readInsuranceCard();
        DataManager.readClaim();

        ClaimSystem.run();
    }

}
