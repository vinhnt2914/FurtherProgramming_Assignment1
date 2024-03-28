package ClaimManagementSystem.Model;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

public interface ClaimProcessManager {
    void add(Claim claim);
    void delete(Claim claim);
    Claim getOne(String id);
    List<Claim> getAll();
}
