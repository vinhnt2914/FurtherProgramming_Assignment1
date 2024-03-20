package ClaimManagementSystem.Utility;

import ClaimManagementSystem.DataManager;
import ClaimManagementSystem.Model.Claim;
import ClaimManagementSystem.Model.ClaimProcessManager;

import java.util.Map;

public class ClaimService implements ClaimProcessManager {
    @Override
    public void add(Claim claim) {
        // Add the claim to system and database
        DataManager.getClaims().put(claim.getId(), claim);
        DataManager.writeClaim(claim);
    }

    /**
     * <p>
     *     Search for the claim in database then overwrite it
     * </p>
     * @param claim Updated claim
     * */
    @Override
    public void update(Claim claim) {

    }

    @Override
    public void delete(Claim claim) {

    }

    @Override
    public Claim getOne(Claim claim) {
        return null;
    }

    @Override
    public Map<String, Claim> getAll() {
        return null;
    }
}
