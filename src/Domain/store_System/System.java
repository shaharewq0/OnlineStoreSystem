package Domain.store_System;

import Domain.RedClasses.IUser;
import Domain.RedClasses.IshoppingBasket;
import Domain.RedClasses.User;
import Domain.RedClasses.shoppingCart;
import Domain.Store.*;
import Domain.info.ProductDetails;
import Domain.info.StoreInfo;
import Domain.store_System.Roles.Member;
import Domain.store_System.Roles.Registered;
import Domain.store_System.Security.PassProtocol_Imp;
import Domain.store_System.Security.PasswordProtocol;
import extornal.payment.MyPaymentSystem;
import extornal.payment.MyPaymentSystem_Driver;
import extornal.payment.PaymentMethed;
import extornal.supply.MySupplySystem;
import extornal.supply.MySupplySystem_Driver;
import extornal.supply.Supplyer;

import java.util.*;

public class System implements ISystem {

	private int TempGuestID = 1;
	private Map<Integer, User> guest = new HashMap<>();
	private Map<String, Registered> membersprofiles = new HashMap<>();
	private Map<User, Member> onlinemember = new HashMap<>();
	private PasswordProtocol myProtocol = PassProtocol_Imp.getInstance();
	// private List<Registered> registered = new LinkedList<>();
	private List<StoreImp> stores = new LinkedList<>();
	private List<MyPair<String, List<shoppingCart>>> order = new LinkedList<>();
	private MyPaymentSystem paymentdriver = new MyPaymentSystem_Driver();
	private MySupplySystem supplydriver = new MySupplySystem_Driver();

	private static System instance = null;

	public void resetSystem(){
		instance = null;	//	TODO: temp
	}

	public int ImNew() {
		TempGuestID++;
		guest.put(TempGuestID, new User());
		return TempGuestID;
	}

	public User getGuest(int id) {
		return guest.get(id);
	}

	public void init(Object Firstuser) {
		// temp
	}

	public static System getInstance() {
		if (instance == null) {
			instance = new System();
		}
		return instance;
	}

	public boolean register(String id, String password) {
		if (!myProtocol.addRegistry(id, password)) {
			return false;
		}

		membersprofiles.put(id, new Registered(id));
		return true;
	}

	// TODO delete this
	public Registered login(String id, String password) {
		Registered reg = Registered_contains(id);
		if (reg == null) {
			return null;
		}
		if (!myProtocol.login(id, password)) {
			return null;
		}
		return reg;
	}

	public Registered login(String id, String password, User user) {
		if (!myProtocol.login(id, password))
			return null;

		Registered Profile = Registered_contains(id);

		Profile.LogLogin(user);
		onlinemember.put(user, new Member(user));
		return Profile;

	}

	public Registered Registered_contains(String id) {
		for (Registered existing : membersprofiles.values()) {
			if (existing.getId().equals(id)) {
				return existing;
			}
		}
		return null;
	}

	private MyPair<String, List<shoppingCart>> containsB(String id, List<MyPair<String, List<shoppingCart>>> toSearch) {
		for (MyPair<String, List<shoppingCart>> existing : toSearch) {
			if (existing.getKey().equals(id)) {
				return existing;
			}
		}
		return null;
	}

	public StoreImp getStoreDetails(String name) {
		for (StoreImp s : stores) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	public List<StoreImp> getAllStores() {
		return stores;
	}

	public Collection<ProductDetails> getProductsFromStore(String name) {

		for (StoreImp s : stores) {
			if (s.getName().equals(name)) {
				return s.getProductsDetails();
			}
		}
		return null;
	}

	public List<ProductDetails> searchProductsByName(String name) {
		List<ProductDetails> toReturn = new LinkedList<>();
		for (StoreImp s : stores) {
			ProductDetails toAdd = s.findProductDetailsByName(name);
			if (toAdd != null) {
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}

	public List<ProductDetails> searchProductsByCategory(String category) {
		List<ProductDetails> toReturn = new LinkedList<>();
		for (StoreImp s : stores) {
			List<ProductDetails> toAdd = s.findProductDetailsByCategory(category);
			concat2(toReturn, toAdd);
		}
		return toReturn;
	}

	public List<ProductDetails> searchProductsByKeyword(String keyword) {
		List<ProductDetails> toReturn = new LinkedList<>();
		for (StoreImp s : stores) {
			List<ProductDetails> toAdd = s.findProductDetailsByKeyword(keyword);
			concat2(toReturn, toAdd);
		}
		return toReturn;
	}

	private void concat(List<Product> a, List<Product> b) {
		for (Product p : b) {
			if (!a.contains(p)) {
				a.add(p);
			}
		}
	}

	private void concat2(List<ProductDetails> a, List<ProductDetails> b) {
		for (ProductDetails p : b) {
			if (!a.contains(p)) {
				a.add(p);
			}
		}
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

	public boolean memberPurchase(String id, shoppingCart cart, int creditCard, String address) {
		if (purchase(cart, creditCard, address)) {
			MyPair<String, List<shoppingCart>> toChange = containsB(id, order);
			if (toChange == null) {
				List<shoppingCart> cartAdd = new LinkedList<>();
				cartAdd.add(cart);
				order.add(new MyPair<>(id, cartAdd));
				return true;
			} else {
				order.remove(toChange);
				toChange.getValue().add(cart);
				order.add(toChange);
				return true;
			}
		}
		return false;
	}

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

	// TODO implement
	public Supplyer navigateSupply() {
		return supplydriver.getSupplayer();

	}

	public StoreImp openStore(StoreInfo store) {
		for (StoreImp s : stores) {
			if (s.getName().equals(store.name)) {
				return null;
			}
		}
		StoreImp newStore = new StoreImp(store);
		stores.add(newStore);
		return newStore;
	}
	
	public StoreImp openStore(String name, String address, int rating) {
		for (StoreImp s : stores) {
			if (s.getName().equals(name)) {
				return null;
			}
		}
		StoreImp newStore = new StoreImp(name, address, rating);
		stores.add(newStore);
		return newStore;
	}

	public List<shoppingCart> orderHistory(String id) {
		MyPair<String, List<shoppingCart>> toReturn = containsB(id, order);
		if (toReturn == null) {
			return new LinkedList<>();
		}
		return toReturn.getValue();
	}

	// @Override
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

	// @Override
	public boolean CheckItemAvailableA(List<ProductDetails> items) {
		for (ProductDetails details : items) {
			if (!getStoreDetails(details.getStoreName()).CheckItemAvailable(details)) {
				return false;
			}
		}
		return true;
	}

	// @Override
	public List<ProductDetails> CheckItemAvailableB(List<ProductDetails> items) {
		List<ProductDetails> Available = new LinkedList<>();
		for (ProductDetails details : items) {
			if (getStoreDetails(details.getStoreName()).CheckItemAvailable(details)) {
				Available.add(details);
			}
		}
		return Available;
	}

	public boolean fillStore(List<Product> Products) {
		boolean output = true;
		for (Product product : Products) {
			output = output & product.getStore().addProduct(product);
		}
		return output;
	}

	public User getMember(String myusername, String myPassword) {
		if (!myProtocol.login(myusername, myPassword))
			return null;
		if (onlinemember.containsKey(myusername))
			return onlinemember.get(myusername).getUser();
		return null;
	}

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
	
	public boolean LogOut(int guestID)
	{
		return false;
		//TODO imp
	}

	
	public User getMember(int guestId) {
		User u =  guest.get(guestId);
		if(onlinemember.containsValue(u))
			return u;
		return null;
	}

	public List<Purchase> getPurchaseHistory(String storeName) {
		return stores.get(stores.indexOf(storeName)).viewPurchaseHistory();
		//return null;
	}



}
