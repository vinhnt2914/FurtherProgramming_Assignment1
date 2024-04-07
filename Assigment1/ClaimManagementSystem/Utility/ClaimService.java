package ClaimManagementSystem.Utility;

import ClaimManagementSystem.Model.Claim;
import ClaimManagementSystem.Model.ClaimProcessManager;
import ClaimManagementSystem.Model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class ClaimService implements ClaimProcessManager {

    @Override
    public void add(Claim claim) {
        // Add the claim to system and database
        DataManager.getClaims().put(claim.getId(), claim);
        DataManager.writeClaim(claim);
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
    public void updateClaimDate(Claim claim, LocalDate newDate) {
        claim.setClaimDate(newDate);
    }

    @Override
    public void updateInsuredPerson(Claim claim, Customer newInsuredPerson) {
        swapClaim(claim, newInsuredPerson);
    }

    /**
     * <p>
     *     Helper function for swapping claim between 2 customers
     * </p>
     * */
    private void swapClaim(Claim claim, Customer newInsuredPerson) {
        claim.getInsuredPerson().removeClaim(claim);
        claim.setInsuredPerson(newInsuredPerson);
        newInsuredPerson.addClaim(claim);
        // Update the card number in claim to new insured person's card
        updateCardNumber(claim, newInsuredPerson.getInsuranceCard().getCardNumber());
    }

    @Override
    public void updateCardNumber(Claim claim, String newCardNumber) {
        claim.setCardNumber(newCardNumber);
    }

    @Override
    public void updateExamDate(Claim claim, LocalDate newDate) {
        claim.setExamDate(newDate);
    }

    @Override
    public void updateClaimAmount(Claim claim, double newClaimAmount) {
        claim.setClaimAmount(newClaimAmount);
    }

    @Override
    public void updateClaimStatus(Claim claim, Claim.ClaimStatus newStatus) {
        claim.setStatus(newStatus);
    }

    @Override
    public void updateBankName(Claim claim, String newBankName) {
        claim.setBankName(newBankName);
    }

    @Override
    public void updateReceiver(Claim claim, String newReceiver) {
        claim.setReceiverName(newReceiver);
    }

    @Override
    public void updateBankNumber(Claim claim, String newBankNumber) {
        claim.setBankNumber(newBankNumber);
    }

    @Override
    public void updateDocuments(Claim claim, List<String> newDocuments) {
        if (!newDocuments.isEmpty()) claim.setDocuments(newDocuments);
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
