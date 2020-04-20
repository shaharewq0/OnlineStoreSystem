package tests.AcceptanceTests;

import java.util.LinkedList;
import java.util.List;

import Store.IStore;
import Store.IUser;
import Store.Product;
import Store.StoreImp;
import Store.shoppingCart;
import store_System.Registered;
import store_System.System;
import tests.AcceptanceTests.auxiliary.ProductDetails;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;
import tests.AcceptanceTests.auxiliary.Question;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class SystemAdapter {
	public void init() {
		// Note: call with DummyPayment and DummySupply
		// Note: also need to delete all?
	}

	public boolean login(String username, String password) {
		// TODO imp
		return System.getInstance().login(username, password) != null;
	}

	public boolean register(String username, String password) {
		return System.getInstance().register(username, password);
	}

	public void logout() {
		// TODO not implemented as of yet
	}

	public boolean isLoggedIn() {
		// TODO function not implemented
		return false;
	}

	public boolean openStore(StoreDetails storeDetails) {
		return System.getInstance().openStore(storeDetails.getName(), "London", 9) != null;
	}

	public boolean hasStore(String storeName) {
		return System.getInstance().getStoreDetails(storeName) != null;
	}

	public String getStoreManager(String validStoreName) {
		return System.getInstance().getStoreDetails(validStoreName).getManagers().toString();
	}

	public List<PurchaseDetails> getPurchaseHistory(String username) {
		List<PurchaseDetails> temp = new LinkedList<PurchaseDetails>();
		for (shoppingCart cart: System.getInstance().orderHistory(username)) {
			temp.add(new PurchaseDetails(cart));
		}
		return temp;
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
		System system = System.getInstance();
		Product p = system.searchProductsByName(productName).get(0);
		return system.getStoreDetails(storeName).addProduct(p);
		// we assume the product and store exist....
	}

	public boolean RemoveProduct(String storeName, String productName) {
		System system = System.getInstance();
		Product p = system.searchProductsByName(productName).get(0);
		return system.getStoreDetails(storeName).removeProduct(p);
	}

	public boolean appointStoreOwner(String username) {
		// TODO what??? a system who appoints a Owner???
		return false;
	}

	public boolean isStoreOwner(String storeName, String username) {
		System system = System.getInstance();
		StoreImp storeImp = system.getStoreDetails(storeName);
		// TODO don't know how to reach user from username
		return false;
	}

	public boolean appointStoreManager(String username) {
		// TODO same as above
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
