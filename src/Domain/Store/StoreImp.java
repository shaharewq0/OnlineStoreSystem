package Domain.Store;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.RedClasses.IUser;
import Domain.Store.workers.Creator;
import Domain.Store.workers.StoreManager_Imp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import extornal.supply.Packet_Of_Prodacts;

public class StoreImp implements IStore {
	private String name;
	private Creator creator;
	private Store_Inventory inventory = new Store_Inventory();
	private Map<String, StoreOwner_Imp> Owners = new HashMap<String, StoreOwner_Imp>();
	private Map<String, StoreManager_Imp> Managers = new HashMap<String, StoreManager_Imp>();
	private String address;
	private int rating;
	private List<Purchase> purchaseHistory = new LinkedList<Purchase>();
	private Map<Product, List<Discount>> discounts = new HashMap<Product, List<Discount>>();

	public StoreImp(String name, Collection<Product> products, String address, int rating) {
		this.name = name;
		inventory.recive_item(new Packet_Of_Prodacts(products));
		// this.products = products;
		this.address = address;
		this.rating = rating;
	}

	public StoreImp(String name, String address, int rating) {
		this.name = name;
		this.address = address;
		this.rating = rating;

	}

	public StoreImp(StoreInfo store) {
		name = store.name;
		address = store.address;
		rating = store.rating;

	}

	public void myCreator(Creator c) {
		creator = c;
	}

	// ----------------------------------------------------------------------------------------my
	// info

	public String getName() {
		return name;
	}

	public Collection<Product> getProducts() {
		return inventory.items.values();
	}

	public List<ProductDetails> getProductsDetails() {
		return ProductDetails.adapteProdactList(inventory.items.values());
	}

	public String getAddress() {
		return address;
	}

	public int getRating() {
		return rating;
	}

	public StoreInfo getMyInfo() {
		return new StoreInfo(name, address, rating, getProductsDetails());
	}

	// ----------------------------------------------------------------------------------------
	// workers
	public Collection<StoreOwner_Imp> getOwners() {
		return Owners.values();
	}

	public Collection<StoreManager_Imp> getManagers() {
		return Managers.values();
	}

	@Override
	public List<Purchase> viewPurchaseHistory() {

		return purchaseHistory;
	}

	@Override
	public boolean fireManager(IUser user) {
		// asked by owner/manager that given user is under them already so no need for
		// logic here
		return Managers.remove(user.getName()) != null;
	}

	@Override
	public boolean appointManager(StoreManager_Imp worker) {
		if (Managers.containsKey(worker.getName()))
			return false;
		return Managers.put(worker.getName(), worker) != null;
	}

	@Override
	public boolean appointOwner(StoreOwner_Imp user) {
		if (Owners.containsKey(user.getName()))
			return false;
		return Owners.put(user.getName(), user) != null;
	}

	public boolean editManagerPermesions(String managername, List<String> permesions) {
		return Managers.get(managername).getNewPermesions(permesions);
	}

//-------------------------------------------------------------------------- products --	

	@Override
	public boolean editProduct(String OLD_p, Product NEW_p) {
		return inventory.editProduct(OLD_p, NEW_p);
	}

	@Override
	public boolean removeProduct(String pName) {
		return inventory.removeProduct(pName);
	}

	public boolean addProduct(Product p) {
		if (!p.getStore().getName().equals(name)) {
			return false;
		}

		return inventory.recive_item(new Packet_Of_Prodacts(p));
	}

	public boolean addProduct(ProductDetails p) {
		return inventory.recive_item(p);
	}

	public Product findProductByName(String name) {
		return inventory.items.get(name);

	}

	public ProductDetails findProductDetailsByName(String name) {
		return new ProductDetails(inventory.items.get(name), inventory.items.get(name).getAmount());

	}

	public List<Product> findProductByCategory(String category) {
		return inventory.findProductByCategory(category);

	}

	public List<ProductDetails> findProductDetailsByCategory(String category) {
		return ProductDetails.adapteProdactList(inventory.findProductByCategory(category));

	}

	public List<Product> findProductByKeyword(String keyword) {
		return inventory.findProductByKeyword(keyword);

	}

	public List<ProductDetails> findProductDetailsByKeyword(String keyword) {
		return ProductDetails.adapteProdactList(inventory.findProductByKeyword(keyword));

	}

	public Boolean CheckItemAvailable(ProductDetails item) {
		if (findProductByName(item.getName()).getAmount() > item.getAmount())
			return true;

		return false;
	}

// ----------------------------------------------------buying

	public double getPrice(String item) {

		return findProductByName(item).getPrice();
	}

	@Override
	synchronized public Product TakeItem(String name, int amount) {
		Product takeout = null;
		Product temp = findProductByName(name);
		if (temp.getAmount() > amount) {
			takeout = new Product(name, temp.getCategory(), temp.getKeyWords(), temp.getPrice(), amount, this);
			temp.removeAmount(amount);
		} else {
			takeout = new Product(name, temp.getCategory(), temp.getKeyWords(), temp.getPrice(), temp.getAmount(),
					this);
			temp.removeAmount(temp.getAmount());
		}
		return takeout;
	}

	public List<Discount> getDiscounts(String name) {
		Product p = findProductByName(name);
		return discounts.get(p);

	}

	// --------------------------------------------------- questions
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean respondToQuestion(String ansewr, int qustionID) {
		// TODO Auto-generated method stub
		return false;
	}

}
