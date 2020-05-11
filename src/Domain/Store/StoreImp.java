package Domain.Store;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.RedClasses.IUser;
import Domain.RedClasses.IshoppingBasket;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import extornal.supply.Packet_Of_Prodacts;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;

public class StoreImp implements IStore {
	private String name;
	private Store_Inventory inventory = new Store_Inventory();
	// private List<Product> products = new LinkedList<Product>();
	private List<IUser> Owners = new LinkedList<IUser>();
	private List<IUser> Managers = new LinkedList<IUser>();
	private String address;
	private int rating;
	private List<Purchase> purchaseHistory = new LinkedList<Purchase>();
	private Map<Product, List<Discount>> discounts = new HashMap<Product, List<Discount>>();

	private Map<IUser, List<IshoppingBasket>> purcheses = new HashMap<IUser, List<IshoppingBasket>>();

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

	// ----------------------------------------------------------------------------------------
	public List<IUser> getOwners() {
		return Owners;
	}

	public List<IUser> getManagers() {
		return Managers;
	}

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
	@Override
	public List<Purchase> viewPurchaseHistory() {
		// throw new NotImplementedException();
		// i don't know how we do this
		// how does purchase look?
		// TODO
		return new LinkedList<Purchase>();
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

	public boolean editManagerPermesions(String managername, List<String> permesions) {
		// TODO Auto-generated method stub
		return false;
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
		return  ProductDetails.adapteProdactList(inventory.findProductByCategory(category));

	}

	public List<Product> findProductByKeyword(String keyword) {
		return inventory.findProductByKeyword(keyword);

	}
	public List<ProductDetails> findProductDetailsByKeyword(String keyword) {
		return ProductDetails.adapteProdactList(inventory.findProductByKeyword(keyword));

	}
// --------------------------------------
	public Boolean CheckItemAvailable(ProductDetails item) {
		if (findProductByName(item.getName()).getAmount() > item.getAmount())
			return true;

		return false;
	}

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

	public List<Purchase> getPurchaseHistory() {
		return purchaseHistory;
	}

	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean respondToQuestion(String ansewr, int qustionID) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
