package Domain.RedClasses;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StoreImp;
import Domain.Store.workers.Creator;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.Store_role;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import Domain.store_System.System;
import Domain.store_System.Roles.Member;
import Domain.store_System.Roles.Registered;
import Domain.store_System.Roles.System_Manager;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class User implements IUser {

	private shoppingCart cart;
	// imps of new classs digram
	private Registered profile = null;
	private Member logInstanse = null;
	private System_Manager sysMangaer = null;
	// ---- dont need to be here

	// TODO move this to register and call it from member system role
	private Map<String, Store_role> store_roles = new HashMap<String, Store_role>();

	public User() {
		cart = new shoppingCart();
	}

	public User(String address, int creditCardNum) {
		cart = new shoppingCart();
	}

	public boolean login(String id, String password) {
		if (profile == null)
			return false;

		profile = System.getInstance().login(id, password, this);
		logInstanse = System.getInstance().getLogInstase(id, password);
		return profile != null;
//		if (system_role instanceof Guest) {
//			Registered profile = System.getInstance().login(id, password);
//			if (profile != null) {
//				system_role = new Member(profile);
//				return true;
//			}
//		}
//		return false;
	}

	public List<StoreImp> watchAllStores() {
		return System.getInstance().getAllStores();
	}

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
		profile = null;
		logInstanse = null;
		profile.LogLogout(this);
		return true;
//		if (system_role instanceof Member) {
//			system_role = new Guest();
//			return true;
//		}
//		return false;
	}

	public boolean openStore(String storename, String address, int rating) {
		StoreImp mystore= logInstanse.OpenStore( new StoreInfo(storename,address,rating));
		store_roles.put(mystore.getName(), new StoreOwner_Imp(mystore));
		return mystore != null;
	}

	public boolean openStore(StoreInfo store) {
		StoreImp mystore= logInstanse.OpenStore(store);
		store_roles.put(mystore.getName(), new Creator(mystore));
		return mystore != null;

	}

	public List<Purchase> getPurchaseHistory() {
		// TODO imp
		return null;
	}

	public List<Purchase> watchHistory() {
		if (profile == null)
			return null;
		return profile.getPurchesHistory();
//		
//		if (system_role instanceof Member) {
//			return System.getInstance().orderHistory(((Member) system_role).getRegistered().getId());
//		}
//		return null;
	}
//
//	@Override
//	public boolean isOwner() {
//		boolean ans = false;
//		for (Store_role I : store_roles.values()) {
//			if (I instanceof StoreOwner)
//				ans = true;
//		}
//		return ans;
//	}
//
//	@Override
//	public boolean isManager() {
//		// TODO: no manager interface for now
//		return false;
//	}
//
//	@Override
//	public boolean isRegistered() {
//		return system_role instanceof Registered | system_role instanceof Member;
//	}
//

	public shoppingCart getCart() {
		return cart;
	}

	// --------------------------------------------------------------- store actions
	public boolean addProduct(String storeName, Product p) {
//TODO add fail
		return store_roles.get(storeName).addItem(p);
	}

	public boolean editProduct(String storeName, String prodactname, Product newdetail) {
		return store_roles.get(storeName).editItem(prodactname, newdetail);
	}

	public boolean removeProduct(String storeName, String prodactname) {

		return store_roles.get(storeName).removeItem(prodactname);
	}

	public boolean appointOwner(String storeName, String username, String otherPassword) {
		return store_roles.get(storeName).appointOwner(System.getInstance().getMember(username, otherPassword));
	}

	public boolean appointManager(String storeName, String username, String otherPassword) {
		return store_roles.get(storeName).appointManager(System.getInstance().getMember(username, otherPassword));

	}

	public boolean fireManager(String storeName, String username) {
		return store_roles.get(storeName).fire(System.getInstance().getUser(username));
	}

	private String last_store_looked_at = "";
	public List<Question> viewQuestions(String storeName) {
		last_store_looked_at = storeName;
		return store_roles.get(storeName).viewQuestions();
	}

	public boolean giveRespond(String ansewr, int qustionID) {
		return store_roles.get(last_store_looked_at).giveRespond( ansewr,  qustionID);
	}

	public List<Purchase> ViewAquistionHistory(String storeName) {
		if(sysMangaer!= null)
			return sysMangaer.getPurchaseHistory( storeName);
		// TODO add option for sys manager
		return store_roles.get(storeName).getPurchaseHistory();
	}

	public List<Purchase> ViewAquistionHistoryOfUser(String username) {

		return System.getInstance().getUser(username).getPurchaseHistory();
	}

	public boolean editMangagerPermesions(String storename,String managername, List<String> permesions) {
		// TODO Auto-generated method stub
		return store_roles.get(storename).editManagerPermesions( managername, permesions);
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

	static public List<ProductDetails> filterByPrice(int minPrice, int maxPrice) {

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

	
	



}
