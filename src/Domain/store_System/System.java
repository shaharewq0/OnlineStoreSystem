package Domain.store_System;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.RedClasses.IshoppingBasket;
import Domain.RedClasses.User;
import Domain.RedClasses.shoppingCart;
import Domain.Store.IStore;
import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.store_System.Roles.Registered;
import Domain.store_System.Security.PassProtocol_Imp;
import Domain.store_System.Security.PasswordProtocol;
import extornal.payment.MyPaymentSystem;
import extornal.payment.MyPaymentSystem_Driver;
import extornal.payment.PaymentMethed;
import extornal.supply.MySupplySystem;
import extornal.supply.MySupplySystem_Driver;
import extornal.supply.Supplyer;
import tests.AcceptanceTests.auxiliary.ProductDetails;

public class System implements ISystem {

	private int TempGuestID = 1;
	private Map<Integer, User> guest = new HashMap<>();
	private Map<String,Registered> members = new HashMap<>();
	
	private PasswordProtocol myProtocol = PassProtocol_Imp.getInstance();
	private List<Registered> registered = new LinkedList<>();
	private List<StoreImp> stores = new LinkedList<>();
	private List<MyPair<String, List<shoppingCart>>> order = new LinkedList<>();
	private MyPaymentSystem paymentdriver = new MyPaymentSystem_Driver();
	private MySupplySystem supplydriver = new MySupplySystem_Driver();

	private static System instance = null;

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
		registered.add(new Registered(id));
		return true;
	}

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

	public Registered Registered_contains(String id) {
		for (Registered existing : registered) {
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

	public List<Product> getProductsFromStore(String name) {
		for (StoreImp s : stores) {
			if (s.getName().equals(name)) {
				return s.getProducts();
			}
		}
		return null;
	}

	public List<Product> searchProductsByName(String name) {
		List<Product> toReturn = new LinkedList<>();
		for (StoreImp s : stores) {
			Product toAdd = s.findProductByName(name);
			if (toAdd != null) {
				toReturn.add(toAdd);
			}
		}
		return toReturn;
	}

	public List<Product> searchProductsByCategory(String category) {
		List<Product> toReturn = new LinkedList<>();
		for (StoreImp s : stores) {
			List<Product> toAdd = s.findProductByCategory(category);
			concat(toReturn, toAdd);
		}
		return toReturn;
	}

	public List<Product> searchProductsByKeyword(String keyword) {
		List<Product> toReturn = new LinkedList<>();
		for (StoreImp s : stores) {
			List<Product> toAdd = s.findProductByKeyword(keyword);
			concat(toReturn, toAdd);
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

	@Override
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

	@Override
	public boolean CheckItemAvailableA(List<ProductDetails> items) {
		for (ProductDetails details : items) {
			if (!getStoreDetails(details.getStoreName()).CheckItemAvailable(details)) {
				return false;
			}
		}
		return true;
	}

	@Override
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
		// TODO Auto-generated method stub
		return null;
	}
}
