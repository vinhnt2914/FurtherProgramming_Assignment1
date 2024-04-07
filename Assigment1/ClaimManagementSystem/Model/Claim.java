package ClaimManagementSystem.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class Claim {
    private String id;
    private LocalDate claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private LocalDate examDate;
    private List<String> documents;
    private double claimAmount;
    private ClaimStatus status;
    private String bankName;
    private String receiverName;
    private String bankNumber;
    public enum ClaimStatus {
        New,
        Processing,
        Done
    }

    public Claim(String id, LocalDate claimDate, Customer insuredPerson, String cardNumber,
                 LocalDate examDate, List<String> documents, double claimAmount,
                 ClaimStatus status, String bankName, String receiverName, String bankNumber) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankName = bankName;
        this.receiverName = receiverName;
        this.bankNumber = bankNumber;
        insuredPerson.getClaims().add(this);
    }

    public String getId() {
        return id;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public String getBankName() {
        return bankName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    /**
     * <p>
     *     setInsuredPerson will not automatically add the claim to the
     *     person's list of claim, this is to prevent duplication that
     *     might happen if mistakes are made.
     * </p>
     * */
    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Claim Details:\n");
        sb.append(String.format("  - ID: %s\n", id));
        sb.append(String.format("  - Claim Date: %s\n", claimDate));
        sb.append(String.format("  - Insured Person: %s\n", insuredPerson.getName()));
        sb.append(String.format("  - Card Number: %s\n", cardNumber));
        sb.append(String.format("  - Exam Date: %s\n", examDate));
        sb.append(String.format("  - Documents: %s\n", documentsToString()));
        sb.append(String.format("  - Claim Amount: %.2f\n", claimAmount));
        sb.append(String.format("  - Status: %s\n", status));
        sb.append(String.format("  - Bank: %s\n", bankName));
        sb.append(String.format("  - Receiver: %s\n", receiverName));
        sb.append(String.format("  - Bank Number: %s\n", bankNumber));
        return sb.toString();
    }

    private String documentsToString() {
        StringBuilder documentsString = new StringBuilder();
        for (String document : documents) {
            documentsString.append(document).append(",");
        }
        // Remove the trailing comma if there are documents present
        if (!documentsString.isEmpty()) {
            documentsString.deleteCharAt(documentsString.length() - 1);
        } else documentsString.append("none");

        return documentsString.toString();
    }

    public String toData() {
        return String.format("%s,%s,%s,%s,%s,%s,%.2f,%s,%s,%s,%s",
                id,
                claimDate,
                insuredPerson.getId(),
                cardNumber,
                examDate,
                documentsToString(),
                claimAmount,
                status,
                bankName,
                receiverName,
                bankNumber
                );
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Claim claim = (Claim) obj;
        return id.equals(claim.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
