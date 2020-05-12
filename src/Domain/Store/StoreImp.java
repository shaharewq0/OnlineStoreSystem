package Domain.Store;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
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
	
	private Store_Purchase_History purchaseHistory = new Store_Purchase_History();
	private Map<Product, List<Discount>> discounts = new HashMap<Product, List<Discount>>();
	private Map<Integer, Question> questions = new HashMap<Integer, Question>();

	public StoreImp(String name, Collection<Product> products, String address, int rating) {

		this.name = name;
		inventory.recive_item(new Packet_Of_Prodacts(products));
		// this.products = products;
		this.address = address;
		this.rating = rating;
		EventLogger.GetInstance().Add_Log(this.toString() + "- new Store is created");
	}

	public StoreImp(String name, String address, int rating) {
		this.name = name;
		this.address = address;
		this.rating = rating;
		EventLogger.GetInstance().Add_Log(this.toString() + "- new Store is created without items");

	}

	public StoreImp(StoreInfo store) {
		name = store.name;
		address = store.address;
		rating = store.rating;
		EventLogger.GetInstance().Add_Log(this.toString() + "- new Store is created without itmes");

	}

	public void myCreator(Creator c) {
		creator = c;
	}

	// ----------------------------------------------------------------------------------------my
	// info
	public Creator getCreator() {
		return creator;
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
	// workers
	public Collection<StoreOwner_Imp> getOwners() {
		return Owners.values();
	}

	public Collection<StoreManager_Imp> getManagers() {
		return Managers.values();
	}

	@Override
	public List<StorePurchase> viewPurchaseHistory() {

		return purchaseHistory.PastPurchase;
	}

	@Override
	public boolean fireManager(String user) {
		EventLogger.GetInstance().Add_Log(this.toString() + "- fire manager from store");
		if(Managers.containsKey(user) && Owners.containsKey(user))
			ErrorLogger.GetInstance().Add_Log(this.toString() + "- fatel error worker is owner and manager");
		if(Managers.containsKey(user)) {
			if(Managers.get(user).getfire())
			return Managers.remove(user) != null;
			
		}
		if(Owners.containsKey(user)) {
			if(Owners.get(user).getfire())
			return Owners.remove(user) != null;
		}
		
		return false;
	}

//	@Override
//	public boolean fireOwner(StoreOwner_Imp user) {
//		EventLogger.GetInstance().Add_Log(this.toString() + "- fire Owner from store");
//		return Owners.remove(user.getName()) != null;
//	}

	@Override
	public boolean appointManager(StoreManager_Imp worker) {
		if (Managers.containsKey(worker.getName()) || Owners.containsKey(worker.getName())) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "cant have 2 workers with same names");
			return false;
		}
		EventLogger.GetInstance().Add_Log(this.toString() + "- appoint new manager in store");
		return Managers.put(worker.getName(), worker) != null;
	}

	@Override
	public boolean appointOwner(StoreOwner_Imp worker) {
		if (Managers.containsKey(worker.getName()) || Owners.containsKey(worker.getName())) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "cant have 2 workers with same names");
			return false;
		}
		EventLogger.GetInstance().Add_Log(this.toString() + "- appoint new manager in store");
		return Owners.put(worker.getName(), worker) != null;
	}

	public boolean editManagerPermesions(String managername, List<String> permesions) {
		StoreManager_Imp m = Managers.get(managername);
		if (m != null) {
			EventLogger.GetInstance().Add_Log(this.toString() + "- edit manager permesions");
			return m.getNewPermesions(permesions);
		}
		ErrorLogger.GetInstance().Add_Log(this.toString() + "edit manager manager dont exsist");

		return false;
	}

//-------------------------------------------------------------------------- products --	

	@Override
	public boolean editProduct(String OLD_p, Product NEW_p) {
		EventLogger.GetInstance().Add_Log(this.toString() + "- edit item");

		return inventory.editProduct(OLD_p, NEW_p);
	}

	@Override
	public boolean removeProduct(String pName) {
		EventLogger.GetInstance().Add_Log(this.toString() + "-remove product");

		return inventory.removeProduct(pName);
	}

	public boolean addProduct(Product p) {

		if (!p.getStore().getName().equals(name)) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "add product - product store not currect");

			return false;
		}
		EventLogger.GetInstance().Add_Log(this.toString() + "-add product");

		return inventory.recive_item(new Packet_Of_Prodacts(p));
	}

	public boolean addProduct(ProductDetails p) {
		EventLogger.GetInstance().Add_Log(this.toString() + "-add product");

		return inventory.recive_item(p);
	}

	public Product findProductByName(String name) {
		return inventory.items.get(name);

	}

	public ProductDetails findProductDetailsByName(String name) {
		if (inventory.items.containsKey(name))
			return new ProductDetails(inventory.items.get(name), inventory.items.get(name).getAmount());
		return null;
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
		if (findProductByName(item.getName()) == null)
			return false;
		if (findProductByName(item.getName()).getAmount() > item.getAmount())
			return true;

		return false;
	}

// ----------------------------------------------------buying

	public double getPrice(String item) {
		if(findProductByName(item)== null)
			ErrorLogger.GetInstance().Add_Log(this.toString() + "- cant find item to calc price");
			
		//TODO add discount type 1 here

		return findProductByName(item).getPrice();
	}

	@Override
	synchronized public Product TakeItem(String name, int amount) {
		Product takeout = null;
		Product temp = findProductByName(name);
		if(temp == null) {
			ErrorLogger.GetInstance().Add_Log(this.toString() + "-takeitem cant find proudct");
			return null;
		}
		if (temp.getAmount() > amount) {
			EventLogger.GetInstance().Add_Log(this.toString() +"- taking out products full amount");
			takeout = new Product(name, temp.getCategory(), temp.getKeyWords(), temp.getPrice(), amount, this);
			temp.removeAmount(amount);
		} else {
			EventLogger.GetInstance().Add_Log(this.toString() +"- taking out products not full amount");
			takeout = new Product(name, temp.getCategory(), temp.getKeyWords(), temp.getPrice(), temp.getAmount(),
					this);
			temp.removeAmount(temp.getAmount());
		}
		return takeout;
	}

	public boolean addPurchase(StorePurchase p)
	{
		purchaseHistory.PastPurchase.add(p);
		return true;
	}
	
	public List<Discount> getDiscounts(String name) {
		Product p = findProductByName(name);
		return discounts.get(p);

	}

	
	// --------------------------------------------------- questions
	public Collection<Question> getQuestions() {

		return questions.values();
	}

	public boolean respondToQuestion(String ansewer, int qustionID) {
		questions.get(qustionID).addAnsewers(ansewer);
		return true;

	}

}
