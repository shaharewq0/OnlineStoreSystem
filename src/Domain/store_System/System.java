package Domain.store_System;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.RedClasses.IUser;
import Domain.RedClasses.IshoppingBasket;
import Domain.RedClasses.User;
import Domain.RedClasses.shoppingCart;
import Domain.Store.IStore;
import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.info.StoreInfo;
import Domain.store_System.Roles.Member;
import Domain.store_System.Roles.Registered;
import Domain.store_System.Roles.System_Manager;
import Domain.store_System.Security.PassProtocol_Imp;
import Domain.store_System.Security.PasswordProtocol;
import extornal.payment.MyPaymentSystem;
import extornal.payment.MyPaymentSystem_Driver;
import extornal.payment.PaymentMethed;
import extornal.supply.MySupplySystem;
import extornal.supply.MySupplySystem_Driver;
import extornal.supply.Supplyer;

public class System implements ISystem {

	private boolean init = false;
	private System_Manager manager = null;
	private int TempGuestID = 1;
	private Map<Integer, User> guest = new HashMap<>();
	private Map<String, Registered> membersprofiles = new HashMap<>();
	private Map<String, Member> onlinemember = new HashMap<>();
	private PasswordProtocol myProtocol = PassProtocol_Imp.getInstance();
	private Map<String, StoreImp> stores = new HashMap<String, StoreImp>();
	private List<MyPair<String, List<shoppingCart>>> order = new LinkedList<>();
	private MyPaymentSystem paymentdriver = new MyPaymentSystem_Driver();
	private MySupplySystem supplydriver = new MySupplySystem_Driver();

	private static System instance = null;

	public static System getInstance() {
		if (instance == null) {
			instance = new System();
		}
		return instance;
	}

	// ----------------------------------init
	public System_Manager ImManeger(String id, String password) {

		if (!(id.compareTo(manager.name) == 0) || !myProtocol.login(id, password)) {
			return null;
		}

		EventLogger.GetInstance().Add_Log(this.toString() + "-manager login");
		return manager;
	}

	public boolean init(String username, String password) {

		if (init) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "- system trying to init 2nd time");
			return false;
		}
		EventLogger.GetInstance().Add_Log(this.toString() + "- system init");
		init = true;
		int guestId = ImNew();
		User guest = getGuest(guestId);
		User.register(username, password);
		manager = new System_Manager(username);
		guest.login(username, password);

		return true;

	}

	// ---------------------------------- users
	public int ImNew() {
		EventLogger.GetInstance().Add_Log(this.toString() + "- new guest");
		TempGuestID++;
		guest.put(TempGuestID, new User());
		return TempGuestID;
	}

	public User getGuest(int id) {
		EventLogger.GetInstance().Add_Log(this.toString() + "- new guest");
		return guest.get(id);
	}

	public boolean register(String id, String password) {
		if (!myProtocol.addRegistry(id, password)) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "- failed to register");
			return false;
		}

		EventLogger.GetInstance().Add_Log(this.toString() + "- new register");
		membersprofiles.put(id, new Registered(id));
		return true;
	}

	public Registered login(String id, String password, User user) {
		if (!myProtocol.login(id, password)) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "- failed to login");
			return null;
		}

		Registered Profile = membersprofiles.get(id);
		if (Profile == null) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "- register dont exsist fatal error");
			return null;
		}
		Profile.LogLogin(user);
		onlinemember.put(id, new Member(user));
		EventLogger.GetInstance().Add_Log(this.toString() + "- user login");
		return Profile;

	}

	public User getMember(String myusername, String myPassword) {
		if (!myProtocol.login(myusername, myPassword))
			return null;
		if (onlinemember.containsKey(myusername))
			return onlinemember.get(myusername).getUser();
		return null;
	}
	
	// -------------------------------Store
	public StoreImp openStore(StoreInfo store) {
		if (stores.containsKey(store.name))
			return null;

		StoreImp newStore = new StoreImp(store);
		stores.put(store.name, newStore);
		return newStore;
	}
	
	// TODO delete one of thouse functions
	public StoreImp openStore(String name, String address, int rating) {
		StoreInfo store = new StoreInfo(name, address, rating);
		if (stores.containsKey(store.name))
			return null;

		StoreImp newStore = new StoreImp(store);
		stores.put(store.name, newStore);
		return newStore;
	}

	public boolean fillStore(List<Product> Products) {
		boolean output = true;
		for (Product product : Products) {
			output = output & product.getStore().addProduct(product);
		}
		return output;
	}
	
	public StoreImp getStoreDetails(String name) {
		if (stores.containsKey(name))
			return stores.get(name);

		return null;
	}

	public Collection<ProductDetails> getProductsFromStore(String name) {
		if (stores.containsKey(name))
			return stores.get(name).getProductsDetails();

		return null;

	}

	public List<IshoppingBasket> orderHistory(IStore store) {

		List<IshoppingBasket> baskets = new LinkedList<>();

		for (MyPair<String, List<shoppingCart>> pair : order) {
			List<shoppingCart> carts = pair.getValue();

			for (shoppingCart cart : carts) {
				for (IshoppingBasket basket : cart.getBaskets()) {
					if (basket.getStore() == store) {
						baskets.add(basket);
					}
				}
			}
		}
		return baskets;
	}

	// ------------------------------ find products
	public List<ProductDetails> searchProductsByName(String name) {
		List<ProductDetails> toReturn = new LinkedList<>();
		for (StoreImp s : stores.values()) {
			ProductDetails toAdd = s.findProductDetailsByName(name);
			if (toAdd != null) {
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}

	public List<ProductDetails> searchProductsByCategory(String category) {
		List<ProductDetails> toReturn = new LinkedList<>();
		for (StoreImp s : stores.values()) {
			List<ProductDetails> toAdd = s.findProductDetailsByCategory(category);
			toReturn.addAll(toAdd);
			// concat2(toReturn, toAdd);
		}
		return toReturn;
	}

	public List<ProductDetails> searchProductsByKeyword(String keyword) {
		List<ProductDetails> toReturn = new LinkedList<>();
		for (StoreImp s : stores.values()) {
			List<ProductDetails> toAdd = s.findProductDetailsByKeyword(keyword);
			toReturn.addAll(toAdd);
			// concat2(toReturn, toAdd);
		}
		return toReturn;
	}

	public List<Product> filterByPrice(List<Product> base, double min, double max) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : base) {
			if (p.getPrice() >= min & p.getPrice() <= max) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	public List<Product> filterByRating(List<Product> base, int min, int max) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : base) {
			if (p.getRating() >= min & p.getRating() <= max) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	public List<Product> filterByCategory(List<Product> base, String category) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : base) {
			if (p.getCategory().equals(category)) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	public List<Product> filterByStoreRating(List<Product> base, int min, int max) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : base) {
			if (p.getStore().getRating() >= min & p.getStore().getRating() <= max) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	// ------------------------purchase
	public boolean purchase(shoppingCart cart, int creditCard, String address) {
		if (!UpdateStorage(cart)) {
			return false;
		}
		double price = cart.CalcPrice();
		// TODO i dont think we need this functions
		// navigatePayment(creditCard, price);
		// navigateSupply(cart, address);
		return true;
	}

	public PaymentMethed navigatePayment() {
		return paymentdriver.getPaymentMethed();
	}

	// TODO implement
	private boolean UpdateStorage(shoppingCart cart) {
		return true;
	}

	public Supplyer navigateSupply() {
		return supplydriver.getSupplayer();

	}



	// @Override
	public boolean CheckItemAvailableA(List<ProductDetails> items) {
		for (ProductDetails details : items) {
			// StoreImp s = s
			if (!getStoreDetails(details.getStoreName()).CheckItemAvailable(details)) {
				return false;
			}
		}
		return true;
	}

	// @Override
//	public List<ProductDetails> CheckItemAvailableB(List<ProductDetails> items) {
//		List<ProductDetails> Available = new LinkedList<>();
//		for (ProductDetails details : items) {
//			if (getStoreDetails(details.getStoreName()).CheckItemAvailable(details)) {
//				Available.add(details);
//			}
//		}
//		return Available;
//	}

	

	
//
	public IUser getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public Member getLogInstase(String id, String password) {
		if (!myProtocol.login(id, password))
			return null;
		if (onlinemember.containsKey(id))
			return onlinemember.get(id);
		return null;
	}

	public boolean LogOut(int guestID) {
		return false;
		// TODO imp
	}

	public User getMember(int guestId) {
		User u = guest.get(guestId);
		if (onlinemember.containsValue(u))
			return u;
		return null;
	}

	public List<Purchase> getPurchaseHistory(String storeName) {
		return stores.get(storeName).viewPurchaseHistory();

		// return null;
	}

	public Collection<StoreImp> getAllStores() {
		return stores.values();
	}

}
