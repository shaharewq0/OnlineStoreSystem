package AcceptanceTests;

import AcceptanceTests.auxiliary.PurchaseDetails;
import AcceptanceTests.auxiliary.StoreDetails;

import java.util.List;

public class SystemAdapter {
    public void init() {

    }

    public void login(String username, String password) {
    }

    public void register(String username, String password) {
    }

    public void logout() {

    }

    public boolean isLoggedIn() {
        return false;
    }

    public boolean openStore(StoreDetails storeDetails) {
        return false;
    }

    public boolean hasStore(String storeName) {
        return false;
    }

    public String getStoreManager(String validStoreName) {
        return null;
    }

    public List<PurchaseDetails> reviewPurchaseHistory() {
        return null;
    }
}
