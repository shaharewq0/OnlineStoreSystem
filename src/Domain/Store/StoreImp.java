package Domain.Store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tests.AcceptanceTests.auxiliary.PurchaseDetails;

public class StoreImp implements IStore {
	private String name;
	private List<Product> products;
	private List<IUser> Owners;
	private List<IUser> Managers;
	private String address;
	private int rating;

	private Map<IUser, List<IshoppingBasket>> purcheses;

	public StoreImp(String name, List<Product> products, String address, int rating) {
		this.name = name;
		this.products = new LinkedList<>();
		// this.products = products;
		this.address = address;
		this.rating = rating;
		Owners = new LinkedList<>();
		Managers = new LinkedList<>();
		purcheses = new HashMap<>();
	}

	public StoreImp(String name, String address, int rating) {
		this.name = name;
		this.products = new LinkedList<>();
		this.address = address;
		this.rating = rating;
		Owners = new LinkedList<>();
		Managers = new LinkedList<>();
		purcheses = new HashMap<>();

	}

	public List<IUser> getOwners() {
		return Owners;
	}

	public List<IUser> getManagers() {
		return Managers;
	}

	public String getName() {
		return name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public String getAddress() {
		return address;
	}

	public int getRating() {
		return rating;
	}

	@Override
	public List<PurchaseDetails> viewPurchaseHistory() {
		// throw new NotImplementedException();
		// i don't know how we do this
		// how does purchase look?
		// TODO
		return new LinkedList<PurchaseDetails>();
	}

	@Override
	public boolean fireManager(IUser user) {
		// asked by owner/manager that given user is under them already so no need for
		// logic here
		return Managers.remove(user);
	}

	@Override
	public boolean appointManager(IUser user) {
		if (Managers.contains(user))
			return false;
		return Managers.add(user);
	}

	@Override
	public boolean appointOwner(IUser user) {
		if (Owners.contains(user))
			return false;
		return Owners.add(user);
	}

	@Override
	public boolean editProduct(Product OLD_p, Product NEW_p) {
		for (Product p : products) {
			if (p.compare(OLD_p)) {
				p.edit(NEW_p);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeProduct(Product p) {
		return products.remove(p);
	}

	public boolean addProduct(Product p) {
		if (contains(p, products) | !p.getStore().getName().equals(name)) {
			return false;
		}
		products.add(p);
		return true;
	}

	private boolean contains(Product p, List<Product> products) {
		for (Product current : products) {
			if (current.getName().equals(p.getName())) {
				return true;
			}
		}
		return false;
	}

	public Product findProductByName(String name) {
		for (Product p : products) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	public List<Product> findProductByCategory(String category) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : products) {
			if (p.getCategory().equals(category)) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	public List<Product> findProductByKeyword(String keyword) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : products) {
			if (p != null && p.getKeyWords().contains(keyword)) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}
}
