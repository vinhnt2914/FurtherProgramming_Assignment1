package ClaimManagementSystem.Model;

import java.util.HashMap;

import java.util.Map;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(Claim claim);
    Claim getOne(Claim claim);
    Map<String, Claim> getAll();
}