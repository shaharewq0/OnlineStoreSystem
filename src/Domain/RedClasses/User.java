package Domain.RedClasses;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.Store.workers.Creator;
import Domain.Store.workers.Store_role;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import Domain.store_System.Roles.Member;
import Domain.store_System.Roles.Registered;
import Domain.store_System.Roles.System_Manager;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class User implements IUser {

	private shoppingCart cart;
	// imps of new classs digram
	private Registered profile = null;
	private Member logInstanse = null;
	private System_Manager sysMangaer = null;
	// ---- dont need to be here

	// TODO move this to register and call it from member system role

	public User() {
		cart = new shoppingCart();
	}

	public User(String address, int creditCardNum) {
		cart = new shoppingCart();
	}

	public boolean login(String id, String password) {
		if (profile != null)
			return false;

		profile = System.getInstance().login(id, password, this);
		logInstanse = System.getInstance().getLogInstase(id, password);
		sysMangaer = System.getInstance().ImManeger(id, password);
		return profile != null;

	}

//	public List<StoreImp> watchAllStores() {
//		return System.getInstance().getAllStores();
//	}

	public Collection<ProductDetails> watchProductsInStore(String name) {
		return System.getInstance().getProductsFromStore(name);
	}

	// TODO rewrite this fucntion
	// adding a product to a basket. if the product exists add 1 to the amount of
	// the product in the basket
	public boolean saveProductInBasket(String productName, String storeName, int amount) {

		cart.findBasket(storeName).addProduct(productName, amount);
		return true;
//		// - not my
//		
//		StoreImp myStore = System.getInstance().getStoreDetails(storeName);
//		if (myStore == null) {
//			return false;
//		}
//		if (System.getInstance().searchProductsByName(productName).size() == 0) {
//			return false;
//		}
//		List<ProductDetails> Products = System.getInstance().searchProductsByName(productName);
//		if (Products == null) {
//			return false;
//		}
//		ProductDetails toSave = null;
//		for (ProductDetails p : Products) {
//			if ( p.getName().equals(storeName) && myStore.getProducts().contains(p)) {
//				toSave = p;
//			}
//		}
//		if (toSave == null) {
//			return false;
//		}
//		shoppingBasket toAdd = cart.findBasket(myStore);
//		if (toAdd == null) {
//			toAdd = new shoppingBasket(myStore);
//			toAdd.addProduct(productName, 1);
//			// cart.addBasket(toAdd);
//			return true;
//		} else
//			toAdd.addProduct(productName, 1);
//		return true;
	}

	// removing at most amount of num of a product from the basket
	public int deleteProductInBasket(String productName, String storeName, int num) {
		return cart.removeItem(storeName, storeName, num);
//		
//		StoreImp myStore = System.getInstance().getStoreDetails(storeName);
//		if (myStore == null) {
//			return -1;
//		}
//		List<ProductDetails> Products = System.getInstance().searchProductsByName(productName);
//		ProductDetails toDelete = null;
//		for (ProductDetails p : Products) {
//			if (p.getName().equals(storeName) & myStore.getProducts().contains(p)) {
//				toDelete = p;
//			}
//		}
//		if (toDelete == null) {
//			return -1;
//		}
//		shoppingBasket toRemove = cart.findBasket(myStore);
//		if (toRemove == null) {
//			return -1;
//		}
//		return toRemove.removeProduct(productName, num);
	}

	public List<ProductDetails> getProductsInCart() {
		return cart.allProductsInCart();
	}

	public boolean logout() {
		if (profile == null || logInstanse == null)
			return false;
		profile.LogLogout(this);
		profile = null;
		logInstanse = null;
		return true;
//		if (system_role instanceof Member) {
//			system_role = new Guest();
//			return true;
//		}
//		return false;
	}

	public boolean openStore(String storename, String address, int rating) {
		StoreImp mystore = logInstanse.OpenStore(new StoreInfo(storename, address, rating));
		if (profile == null)
			return false;

		profile.store_roles.put(mystore.getName(), new Creator(mystore));
		return mystore != null;
	}

	public boolean openStore(StoreInfo store) {
		StoreImp mystore = logInstanse.OpenStore(store);
		if (profile == null || mystore == null)
			return false;

		profile.store_roles.put(mystore.getName(), new Creator(mystore));
		return mystore != null;

	}

	public List<UserPurchase> getPurchaseHistory() {
		// TODO imp
		return null;
	}

	// TODO check srvice layer
	public List<UserPurchase> watchHistory() {
		if (profile == null)
			return null;
		return profile.getPurchesHistory();

	}

	public shoppingCart getCart() {
		return cart;
	}

	// --------------------------------------------------------------- store actions

	public boolean addProduct(String storeName, Product p) {
//TODO add fail
		if (profile == null)
			return false;

		return profile.store_roles.get(storeName).addItem(p);
	}

	public boolean editProduct(String storeName, String prodactname, Product newdetail) {
		if (profile == null)
			return false;

		return profile.store_roles.get(storeName).editItem(prodactname, newdetail);
	}

	public boolean removeProduct(String storeName, String prodactname) {

		if (profile == null)
			return false;

		return profile.store_roles.get(storeName).removeItem(prodactname);
	}

	public boolean appointOwner(String storeName, String username, String otherPassword) {
		if (profile == null)
			return false;

		return profile.store_roles.get(storeName).appointOwner(System.getInstance().getMember(username, otherPassword));
	}

	// TODO move to register
	@Override
	public boolean appointAsOwner(Store_role role) {
		if (profile == null || profile.store_roles.containsKey(role.getStore().getName())
				&& !profile.store_roles.get(role.getStore().getName()).canPromoteToOwner()) {
			return false;
		}
		profile.store_roles.remove(role.getStore().getName());
		profile.store_roles.put(role.getStore().getName(), role);
		return true;

	}

	public boolean appointManager(String storeName, String username, String otherPassword) {

		if (profile == null)
			return false;

		return profile.store_roles.get(storeName)
				.appointManager(System.getInstance().getMember(username, otherPassword));

	}

	@Override
	public boolean appointAsManager(Store_role role) {
		// TODO this is the same as appoint owner
		if (profile == null)
			return false;
		Map<String, Store_role> store_roles = profile.store_roles;
		if (store_roles.containsKey(role.getStore().getName())
				&& !store_roles.get(role.getStore().getName()).canPromoteToOwner()) {
			return false;
		}
		store_roles.remove(role.getStore().getName());
		store_roles.put(role.getStore().getName(), role);
		return true;
	}

	public boolean fireManager(String storeName, String username) {
		Map<String, Store_role> store_roles = profile.store_roles;
		return store_roles.get(storeName).fire(username);
	}

	@Override
	public boolean getFired(String store) {
		Map<String, Store_role> store_roles = profile.store_roles;
		return store_roles.remove(store) != null;
	}

	private String last_store_looked_at = "";

	public Collection<Question> viewQuestions(String storeName) {
		last_store_looked_at = storeName;
		Map<String, Store_role> store_roles = profile.store_roles;
		return store_roles.get(storeName).viewQuestions();
	}

	public boolean giveRespond(String ansewr, int qustionID) {
		Map<String, Store_role> store_roles = profile.store_roles;
		return store_roles.get(last_store_looked_at).giveRespond(ansewr, qustionID);
	}

	public List<StorePurchase> ViewAquistionHistory(String storeName) {
		if (sysMangaer != null)
			return sysMangaer.getPurchaseHistory(storeName);
		Map<String, Store_role> store_roles = profile.store_roles;
		return store_roles.get(storeName).getPurchaseHistory();
	}

	public List<UserPurchase> ViewAquistionHistoryOfUser(String username) {
		if (sysMangaer != null)
			return sysMangaer.getPurchaseHistoryofUser(username);
		return null;
	}

	public boolean editMangagerPermesions(String storename, String managername, List<String> permesions) {
		Map<String, Store_role> store_roles = profile.store_roles;
		return store_roles.get(storename).editManagerPermesions(managername, permesions);
	}

	// note this functions
	public void Complet_Purchase(double price) {
		UserPurchase purchase = cart.Complet_Purchase();
		purchase.TotalePrice = price;
		if (profile != null) {
			profile.getPurchesHistory().add(purchase);
		}
		// TODO Auto-generated method stub

	}
	// Static -
	// ------------------------------------------------------------------------------------------------------------------

	static public boolean register(String id, String password) {
		return System.getInstance().register(id, password);
	}

	static public StoreDetails watchStoreDetails(String name) {
		StoreImp store = System.getInstance().getStoreDetails(name);
		if (store == null)
			return null;
		return new StoreDetails(store);
	}

	static public StoreInfo watchStoreInfo(String storeName) {
		StoreImp store = System.getInstance().getStoreDetails(storeName);
		if (store == null)
			return null;
		return store.getMyInfo();
	}

	static public List<ProductDetails> searchProductsByName(String name) {
		return System.getInstance().searchProductsByName(name);
	}

	static public List<ProductDetails> searchProductsByCategory(String category) {
		return System.getInstance().searchProductsByCategory(category);
	}

	static public List<ProductDetails> searchProductsByKeyword(String keyword) {
		return System.getInstance().searchProductsByKeyword(keyword);
	}

	static public List<ProductDetails> filterByPrice(double minPrice, double maxPrice) {

		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getPrice() < maxPrice && product.getPrice() > minPrice)
					output.add(new ProductDetails(product, product.getAmount()));
			}
		}

		return output;
	}

	static public List<ProductDetails> filterByRating(int minRating, int maxRating) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getRating() < maxRating && product.getPrice() > minRating)
					output.add(new ProductDetails(product, product.getAmount()));
			}
		}
		return output;
		// return System.getInstance().filterByRating( min, max);
	}

	static public List<ProductDetails> filterByCategory(String category) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getCategory().contains(category))
					output.add(new ProductDetails(product, product.getAmount()));
			}
		}
		return output;
		// return System.getInstance().filterByCategory(category);
	}

	static public List<ProductDetails> filterByStoreRating(int minRating, int maxRating) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			if (store.getRating() > minRating && store.getRating() < maxRating)
				for (Product product : store.getProducts()) {
					output.add(new ProductDetails(product, product.getAmount()));
				}
		}
		return output;
		// return System.getInstance().filterByStoreRating( min, max);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if (profile == null)
			return "Error still no name";
		return profile.getId();
	}

}
