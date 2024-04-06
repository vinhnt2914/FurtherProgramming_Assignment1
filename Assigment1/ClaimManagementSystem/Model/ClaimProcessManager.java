package ClaimManagementSystem.Model;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public interface ClaimProcessManager {
    void add(Claim claim);
    void delete(Claim claim);

    void updateClaimDate(Claim claim, LocalDate newDate);

    void updateInsuredPerson(Claim claim, Customer newInsuredPerson);
    void updateCardNumber(Claim claim, String newCardNumber);

    void updateExamDate(Claim claim, LocalDate newDate);

    void updateClaimAmount(Claim claim, double newClaimAmount);

    void updateClaimStatus(Claim claim, Claim.ClaimStatus newStatus);

    void updateBankName(Claim claim, String newBankName);

    void updateReceiver(Claim claim, String newReceiver);

    void updateBankNumber(Claim claim, String newBankNumber);

    Claim getOne(String id);
    List<Claim> getAll();
}
