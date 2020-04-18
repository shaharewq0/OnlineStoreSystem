package AcceptanceTests;

import AcceptanceTests.auxiliary.ProductDetails;
import AcceptanceTests.auxiliary.PurchaseDetails;
import AcceptanceTests.auxiliary.Question;
import AcceptanceTests.auxiliary.StoreDetails;

import java.util.List;

public class SystemAdapter {
    public void init() {
        //Note: also need to delete all
    }

    public boolean login(String username, String password) {
        return false;
    }

    public boolean register(String username, String password) {
        //Note: only register, doesn't login
        return false;
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

    public boolean isRegistered(String username) {
        return false;
    }

    public StoreDetails getStoreDetails(String storeName) {
        return null;
    }

    public ProductDetails getProductDetails(String storeName, String productName) {
        return null;
    }

    public List<ProductDetails> searchProductByName(String name) {
        return null;
    }

    public List<ProductDetails> searchProductByCategory(String category) {
        return null;
    }

    public List<ProductDetails> searchProductByKeyword(String keyword) {
        return null;
    }

    public boolean inBasket(String storeName, String productName) {
        return false;
    }

    public void addToBasket(String storeName, String productName) {

    }

    public void clearShoppingCart() {

    }

    public List<ProductDetails> getShoppingCart() {
        return null;
    }

    public boolean hasItem(String storeName, String productName) {
        return false;
    }

    public boolean addProductToStore(String storeName, String productName) {
        return false;
    }

    public boolean RemoveProduct(String storeName, String productName) {
        return false;
    }

    public boolean appointStoreOwner(String username) {
        return false;
    }

    public boolean isStoreOwner(String storeName, String username) {
        return false;
    }

    public boolean appointStoreManager(String username) {
        return false;
    }

    public boolean isStoreManager(String storeName, String username) {
        return false;
    }

    public boolean removeStoreManager(String storeName, String username) {
        return false;
    }

    public List<PurchaseDetails> getStoreSellingHistory(String storeName) {
        return null;
    }

    public List<Question> getStoreQuestions(String storeName) {
        return null;
    }

    public void askQuestion(String storeName, Question question) {

    }

    public void answerQuestion(Question question) {

    }
}
