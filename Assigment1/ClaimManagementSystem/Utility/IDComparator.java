package ClaimManagementSystem.Utility;

import java.util.Comparator;
/**
 * @author Nguyen The Vinh - s3979366
 */
public class IDComparator implements Comparator<String> {
    @Override
    public int compare(String id1, String id2) {
        return id1.compareTo(id2);
    }
}
