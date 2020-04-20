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
import store_System.Security.PassProtocol_Imp;
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
		for (shoppingCart cart : System.getInstance().orderHistory(username)) {
			temp.add(new PurchaseDetails(cart));
		}
		return temp;
	}

	public boolean isRegistered(String username) {
		// TODO function dont exsist
		return System.getInstance().Registered_contains(username) != null;
	}

	public StoreDetails getStoreDetails(String storeName) {
		StoreImp store = System.getInstance().getStoreDetails(storeName);
		if (store == null)
			return null;
		return new StoreDetails(store);
	}

	public ProductDetails getProductDetails(String storeName, String productName) {
		Product pro = System.getInstance().getStoreDetails(storeName).findProductByName(productName);
		if (pro == null)
			return null;
		return new ProductDetails(pro);
	}

	public List<ProductDetails> searchProductByName(String name) {
		return ProductDetails.adapteProdactList(System.getInstance().searchProductsByName(name));
	}

	public List<ProductDetails> searchProductByCategory(String category) {
		return ProductDetails.adapteProdactList(System.getInstance().searchProductsByCategory(category));
	}

	public List<ProductDetails> searchProductByKeyword(String keyword) {
		return ProductDetails.adapteProdactList(System.getInstance().searchProductsByKeyword(keyword));
	}

	public boolean inBasket(String storeName, String productName) {
		// TODO dont know which user to check
		return false;
	}

	public void addToBasket(String storeName, String productName) {
		// TODO dont know which user to check
	}

	public void clearShoppingCart() {
		// TODO dont know which user to check
	}

	public List<ProductDetails> getShoppingCart() {
		// TODO dont know which user to check
		return new LinkedList<>();
	}

	public boolean hasItem(String storeName, String productName) {
		return System.getInstance().getStoreDetails(storeName).findProductByName(productName) != null;
	}

	public boolean addProductToStore(String storeName, String productName) {
		System system = System.getInstance();
		// Product p = system.searchProductsByName(productName).get(0);
		StoreImp store = system.getStoreDetails(storeName);
		return store.addProduct(new Product(productName, "cat", new LinkedList<String>(), 5, 1, store));
		// we assume the product and store exist....
	}

	public boolean RemoveProduct(String storeName, String productName) {
		System system = System.getInstance();
		Product p = system.searchProductsByName(productName).get(0);
		return system.getStoreDetails(storeName).removeProduct(p);
	}

	public boolean appointStoreOwner(String username) {
		// TODO need store name and who appoints me
		return false;
	}

	public boolean appointStoreManager(String username) {
		// TODO same as above
		return false;
	}

	public boolean isStoreOwner(String storeName, String username) {
		System system = System.getInstance();
		StoreImp storeImp = system.getStoreDetails(storeName);
		// TODO as of now we cant reach user. by username - we can only reach register.
		// in next version
		// system role will move to register
		return false;
	}

	public boolean isStoreManager(String storeName, String username) {
		// TODO same as above
		return false;
	}

	public boolean removeStoreManager(String storeName, String username) {
		// TODO same as above
		return false;
	}

	public List<PurchaseDetails> getStoreSellingHistory(String storeName) {
		return System.getInstance().getStoreDetails(storeName).viewPurchaseHistory();
	}

	public List<Question> getStoreQuestions(String storeName) {
		// TODO this is not implemented in version 1
		return new LinkedList<>();
	}

	public void askQuestion(String storeName, Question question) {
		// TODO this is not implemented in version 1

	}

	public void answerQuestion(Question question) {
		// TODO this is not implemented in version 1

	}
}
