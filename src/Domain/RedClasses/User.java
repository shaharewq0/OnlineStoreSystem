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
import Domain.Store.workers.StoreOwner;
import Domain.Store.workers.Store_role;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.store_System.System;
import Domain.store_System.Roles.Guest;
import Domain.store_System.Roles.Member;
import Domain.store_System.Roles.Registered;
import Domain.store_System.Roles.System_Role;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class User implements IUser {

	private shoppingCart cart;

	// ---- dont need to be here
	private System_Role system_role;// guest member sysmanager;
	private String address;
	private int creditCardNum;
	// TODO move this to register and call it from member system role
	private Map<String, Store_role> store_roles = new HashMap<String, Store_role>();

	public User() {
		cart = new shoppingCart();
	}

	public User(String address, int creditCardNum) {
		system_role = new Guest();
		cart = new shoppingCart();
		this.address = address;
		this.creditCardNum = creditCardNum;
	}

	public boolean register(String id, String password) {
		if (system_role instanceof Guest) {
			boolean reg = System.getInstance().register(id, password);
			if (reg) {
				return true;
			}
		}
		return false;
	}

	public boolean login(String id, String password) {
		if (system_role instanceof Guest) {
			Registered log = System.getInstance().login(id, password);
			if (log != null) {
				system_role = new Member(log);
				return true;
			}
		}
		return false;
	}

	public StoreImp watchStoreDetails(String name) {
		return System.getInstance().getStoreDetails(name);
	}

	public List<StoreImp> watchAllStores() {
		return System.getInstance().getAllStores();
	}

	public Collection<Product> watchProductsInStore(String name) {
		return System.getInstance().getProductsFromStore(name);
	}

	public List<Product> searchProductsByName(String name) {
		return System.getInstance().searchProductsByName(name);
	}

	public List<Product> searchProductsByCategory(String category) {
		return System.getInstance().searchProductsByCategory(category);
	}

	public List<Product> searchProductsByKeyword(String keyword) {
		return System.getInstance().searchProductsByKeyword(keyword);
	}

	public List<Product> filterByPrice(List<Product> base, int min, int max) {
		return System.getInstance().filterByPrice(base, min, max);
	}

	public List<Product> filterByRating(List<Product> base, int min, int max) {
		return System.getInstance().filterByRating(base, min, max);
	}

	public List<Product> filterByCategory(List<Product> base, String category) {
		return System.getInstance().filterByCategory(base, category);
	}

	public List<Product> filterByStoreRating(List<Product> base, int min, int max) {
		return System.getInstance().filterByStoreRating(base, min, max);
	}

	// adding a product to a basket. if the product exists add 1 to the amount of
	// the product in the basket
	public boolean saveProductInBasket(String productName, String storeName) {
		StoreImp myStore = System.getInstance().getStoreDetails(storeName);
		if (myStore == null) {
			return false;
		}
		if (System.getInstance().searchProductsByName(productName).size() == 0) {
			return false;
		}
		List<Product> Products = System.getInstance().searchProductsByName(productName);
		if (Products == null) {
			return false;
		}
		Product toSave = null;
		for (Product p : Products) {
			if (p.getStore().getName().equals(storeName) && myStore.getProducts().contains(p)) {
				toSave = p;
			}
		}
		if (toSave == null) {
			return false;
		}
		shoppingBasket toAdd = cart.findBasket(myStore);
		if (toAdd == null) {
			toAdd = new shoppingBasket(myStore);
			toAdd.addProduct(productName, 1);
			// cart.addBasket(toAdd);
			return true;
		} else
			toAdd.addProduct(productName, 1);
		return true;
	}

	// removing at most amount of num of a product from the basket
	public int deleteProductInBasket(String productName, String storeName, int num) {
		StoreImp myStore = System.getInstance().getStoreDetails(storeName);
		if (myStore == null) {
			return -1;
		}
		List<Product> Products = System.getInstance().searchProductsByName(productName);
		Product toDelete = null;
		for (Product p : Products) {
			if (p.getStore().getName().equals(storeName) & myStore.getProducts().contains(p)) {
				toDelete = p;
			}
		}
		if (toDelete == null) {
			return -1;
		}
		shoppingBasket toRemove = cart.findBasket(myStore);
		if (toRemove == null) {
			return -1;
		}
		return toRemove.removeProduct(productName, num);
	}

	public List<ProductDetails> getProductsInCart() {
		return cart.allProductsInCart();
	}

//    public boolean purchase(){
//        boolean toReturn;
//        if(system_role instanceof Member ){
//             toReturn= System.getInstance().memberPurchase(((Member) system_role).getRegistered().getId(),cart,creditCardNum,address);
//        }
//        else{
//            toReturn= System.getInstance().purchase(cart,creditCardNum,address);
//        }
//        if (toReturn){
//            cart= new shoppingCart();
//        }
//        return toReturn;
//    }

	public boolean logout() {
		if (system_role instanceof Member) {
			system_role = new Guest();
			return true;
		}
		return false;
	}

	public boolean openStore(String storename, String address, int rating) {
		if (system_role instanceof Member) {
			StoreImp s = System.getInstance().openStore(storename, address, rating);
			if (s != null) {
				store_roles.put(storename, new Creator(s));
				// store_roles.add(new Creator(s));
				return true;
			}
		}
		return false;
	}

	public boolean openStore(StoreDetails store) {
		if (system_role instanceof Member) {
			StoreImp s = System.getInstance().openStore(store.getName(), address, 0);
			if (s != null) {
				store_roles.put(store.getName(), new Creator(s));
				return true;
			}
		}
		return false;
	}

	public List<PurchaseDetails> getPurchaseHistory() {
		return null;
	}

	public List<shoppingCart> watchHistory() {
		if (system_role instanceof Member) {
			return System.getInstance().orderHistory(((Member) system_role).getRegistered().getId());
		}
		return null;
	}

	@Override
	public boolean isOwner() {
		boolean ans = false;
		for (Store_role I : store_roles.values()) {
			if (I instanceof StoreOwner)
				ans = true;
		}
		return ans;
	}

	@Override
	public boolean isManager() {
		// TODO: no manager interface for now
		return false;
	}

	@Override
	public boolean isRegistered() {
		return system_role instanceof Registered | system_role instanceof Member;
	}

	@Override
	public shoppingCart getCart() {
		return cart;
	}

	// ---------------------------------------------------------------
	public boolean addProduct(String storeName, Product p) {

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
	public boolean appointManager(String storeName, String username,String otherPassword) {
		return store_roles.get(storeName).appointManager(System.getInstance().getMember(username, otherPassword));

	}

	public boolean fireManager(String storeName, String username) {
		return store_roles.get(storeName).fire(System.getInstance().getUser(username));
	}

	public List<Question> viewQuestions(String storeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean giveRespond(String ansewr, int qustionID) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Purchase> ViewAquistionHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
