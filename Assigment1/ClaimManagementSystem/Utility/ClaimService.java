package ClaimManagementSystem.Utility;

import ClaimManagementSystem.DataManager;
import ClaimManagementSystem.Model.Claim;
import ClaimManagementSystem.Model.ClaimProcessManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClaimService implements ClaimProcessManager {

    @Override
    public void add(Claim claim) {
        // Add the claim to system and database
        DataManager.getClaims().put(claim.getId(), claim);
    }

    /**
     * <p>
     *     Before deleting a claim, we must delete the claim
     *     from the respective customer's list of claims.
     * </p>
     * */
    @Override
    public void delete(Claim claim) {
        // Delete claim from customer's list of claim
        claim.getInsuredPerson().removeClaim(claim);
        // Remove claim from the system
        DataManager.getClaims().remove(claim.getId());
        // Overwrite the data
        DataManager.overWriteCustomer();
        DataManager.overWriteClaim();
    }

    @Override
    public Claim getOne(String id) {
        return DataManager.getClaim(id);
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(DataManager.getClaims().values());
    }
}
