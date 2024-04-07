package ClaimManagementSystem.Model;

import java.time.LocalDate;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class InsuranceCard {
    private String cardNumber;
    private Customer cardHolder;
    private String policyOwner;
    private LocalDate expirationDate;

    public InsuranceCard(String cardNumber, Customer cardHolder, String policyOwner, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
        cardHolder.setInsuranceCard(this);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Customer getCardHolder() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return String.format("Insurance Card:\n" +
                        "  - Number: %s\n" +
                        "  - Card Holder: %s\n" +
                        "  - Policy Owner: %s\n" +
                        "  - Expiration Date: %s",
                cardNumber, cardHolder.getName(), policyOwner, expirationDate);
    }

    public String toData() {
        return String.format("%s,%s,%s,%s",
                cardNumber,
                cardHolder.getId(),
                policyOwner,
                expirationDate);
    }

}
