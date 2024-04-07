package ClaimManagementSystem.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nguyen The Vinh - s3979366
 */
public abstract class Customer {
    private String id;
    private String name;
    private InsuranceCard insuranceCard;
    private List<Claim> claims;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        claims = new ArrayList<>();
    }

    public void setInsuranceCard(InsuranceCard card) {
        this.insuranceCard = card;
    }

    public void addClaim(Claim claim) {
        this.claims.add(claim);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this instanceof PolicyHolder) {
            sb.append("Customer Details (Policy Holder): \n");
        } else if (this instanceof Dependant) {
            sb.append("Customer Details (Dependant): \n");
        }
        sb.append(String.format("  - ID: %s\n", id));
        sb.append(String.format("  - Name: %s\n", name));
        sb.append(String.format("  - Insurance Card: %s\n", insuranceCard == null ? "None" : insuranceCard.getCardNumber()));
        sb.append(String.format("  - Claims: %s\n", claimsToString()));
//        if (claims.isEmpty()) {
//            sb.append(" none");
//        } else {
//            for (Claim claim : claims) {
//                sb.append(String.format(" %s,", claim.getId()));
//            }
//        }
        return sb.toString();
    }

    private String claimsToString() {
        StringBuilder claimsString = new StringBuilder();
        for (Claim claim : claims) {
            claimsString.append(claim.getId()).append(",");
        }
        // Remove the trailing comma if there are documents present
        if (claimsString.isEmpty()) {
            claimsString.append("none,");
        }

        return claimsString.toString();
    }

    public String toData() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",");
        sb.append(name).append(",");
        sb.append(insuranceCard == null ? null : insuranceCard.getCardNumber()).append(",");
        sb.append(claimsToString());

        if (this instanceof PolicyHolder) {
            for (String id : ((PolicyHolder) this).getDependantsIDS()) {
                sb.append(id).append(",");
            }
        }
        return sb.toString();
    }


    public void update(Claim oldClaim, Claim newClaim) {
        for (Claim claim : claims) {
            if (claim == oldClaim) {
                claims.remove(claim);
                return;
            }
        }
        claims.add(newClaim);
    }

    /**
     * <p>
     *     Remove claim will not set insured person in claim to null.
     *     This is to prevent NullPointerException. Setting insured person
     *     to another person will be handle in other functions
     * </p>
     * */
    public void removeClaim(Claim claim) {
        claims.remove(claim);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Customer customer = (Customer) obj;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
