package tests.AcceptanceTests;

import Domain.RedClasses.shoppingCart;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.*;

import java.util.LinkedList;
import java.util.List;

public class SystemAdapter {
	public void init() {
		// Note: call with DummyPayment and DummySupply
		// Note: also need to delete all?
	}

	public int newGuest() {
		return 0;
	}

	//use case 2
	public boolean register(String username, String password) {
		return System.getInstance().register(username, password);
	}

	public void removeUser(String username) {
		/* TODO */
	}

	public boolean login(String username, String password) {
		// TODO imp
		return System.getInstance().login(username, password) != null;
	}

	public boolean logout(String username, String password) {
		// TODO not implemented as of yet
		return false;
	}

	public boolean isLoggedIn() {
		// TODO REMOVE
		return false;
	}

	public boolean openStore(String username, String password, StoreDetails storeDetails) {
		return System.getInstance().openStore(storeDetails.getName(), "London", 9) != null;
	}

    public boolean openStore(String username, StoreDetails storeDetails) {
        return System.getInstance().openStore(storeDetails.getName(), "London", 9) != null;
    }

	public boolean openStore(StoreDetails storeDetails) {
		return System.getInstance().openStore(storeDetails.getName(), "London", 9) != null;
	}

	// use case 2
	public boolean hasStore(String storeName) {
		return System.getInstance().getStoreDetails(storeName) != null;
	}

	public String getStoreManager(String validStoreName) {
		return System.getInstance().getStoreDetails(validStoreName).getManagers().toString();
	}

	public List<PurchaseDetails> getPurchaseHistory(String username) {
		List<PurchaseDetails> temp = new LinkedList<>();
		for (shoppingCart cart : System.getInstance().orderHistory(username)) {
			temp.add(new PurchaseDetails(cart));
		}
		return temp;
	}

	public boolean isRegistered(String username) {
		// TODO function dont exsist
		return System.getInstance().Registered_contains(username) != null;
	}

	// use case 2
	public StoreDetails getStoreDetails(String storeName) {
		StoreImp store = System.getInstance().getStoreDetails(storeName);
		if (store == null)
			return null;
		return new StoreDetails(store);
	}

	public List<ProductDetails> getProductsFromStore(String storeName) {
		// TODO
		return null;
	}

	//use case 2
	public ProductDetails getProductDetails(String storeName, String productName) {
		Product pro = System.getInstance().getStoreDetails(storeName).findProductByName(productName);
		if (pro == null)
			return null;
		return null; //new ProductDetails(pro, 0);
	}

	// use case 2
	public List<ProductDetails> searchProductByName(String name) {
		return null;// return
					// ProductDetails.adapteProdactList(System.getInstance().searchProductsByName(name));
	}

	// use case 2
	public List<ProductDetails> searchProductByCategory(String category) {
		return null;// return
					// ProductDetails.adapteProdactList(System.getInstance().searchProductsByCategory(category));
	}

	// use case 2
	public List<ProductDetails> searchProductByKeyword(String keyword) {
		return null;
		// return
		// ProductDetails.adapteProdactList(System.getInstance().searchProductsByKeyword(keyword));
	}

	public List<ProductDetails> filterByPrice(double minPrice, double maxPrice) {
		//TODO
		return null;
	}

	public List<ProductDetails> filterByRating(int minRating, int maxRating) {
		//TODO
		return null;
	}

	public List<ProductDetails> filterByStoreRating(int minRating, int maxRating) {
		//TODO
		return null;
	}

	public List<ProductDetails> filterByCategory(String category) {
		//TODO
		return null;
	}

	//use case 2
	public boolean inBasket(String storeName, String productName) {
		// TODO dont know which user to check
		return false;
	}

	public boolean addToBasket(int guestID, String storeName, String productName, int amount) {
		// TODO
		return false;
	}

	//use case 2
	public void clearShoppingCart(int guestID) {
		// TODO
	}
	//use case 2
	public List<ProductDetails> watchShoppingCart(int guestID) {
		// TODO
		return null;
	}

	public boolean removeProductsFromCart(int guestID, String storeName, String productName, int amount) {
		// TODO
		return false;
	}

		//use case 2
	public boolean hasItem(String storeName, String productName) {
		return System.getInstance().getStoreDetails(storeName).findProductByName(productName) != null;
	}

	public boolean addProductToStore(String storeName, String productName) {
		System system = System.getInstance();
		// Product p = system.searchProductsByName(productName).get(0);
		StoreImp store = system.getStoreDetails(storeName);
//		return store.addProduct(new Product(productName, "cat", new LinkedList<>(), 5, 1, store));
		// we assume the product and store exist....
		return false;
	}

	public boolean RemoveProduct(String storeName, String productName) {
//		System system = System.getInstance();
//		Product p = system.searchProductsByName(productName).get(0);
//		return system.getStoreDetails(storeName).removeProduct(productName);
		return false;
	}

	public boolean addProduct(String username, String password, String storeName, ProductDetails productName) {
		//TODO
		return false;
	}

	public boolean RemoveProduct(String username, String password, String storeName, String productName) {
		//TODO
		return false;
	}

	public boolean EditProduct(String username, String password, String storeName, String productName,
							   ProductDetails newProductDet) {
		//TODO
		return false;
	}

    public boolean appointOwner(String myusername, String myPassword, String storeName, String hisusername,String hisPassword) {
		// TODO
		return false;
	}

    public boolean appointManager(String myusername, String myPassword, String storeName, String hisusername,String hisPassword) {
        // TODO
        return false;
    }

    public boolean editManagerPermissions(String myusername, String myPassword,String storename, String managername,
                                          List<Permissions> permissions) {
        // TODO
        return false;
    }

    public boolean fireManager(String myusername, String myPassword, String storeName, String hisusername) {
        // TODO
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
		return null;
		// return System.getInstance().getStoreDetails(storeName).viewPurchaseHistory();
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

	public void removeStore(StoreDetails store) {
		// TODO required for testing
	}
}
