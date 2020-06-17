package Domain.Store;

import java.util.*;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.Policies.Acquisitions.AcquisitionPolicy;
import Domain.Policies.Discounts.DiscountPolicy;
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
    private DiscountPolicy discounts = new DiscountPolicy();
    private AcquisitionPolicy acquisitions = new AcquisitionPolicy();

    private Store_Purchase_History purchaseHistory = new Store_Purchase_History();
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
        return ProductDetails.adapteProdactList(inventory.items.values(), name);
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

    // ---------------------------------------------------------------------------------workers
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
    public boolean fireWorker(String user) {
        EventLogger.GetInstance().Add_Log(this.toString() + "- fire manager from store");
        if (Managers.containsKey(user) && Owners.containsKey(user))
            ErrorLogger.GetInstance().Add_Log(this.toString() + "- fatel error worker is owner and manager");

        if (Managers.containsKey(user)) {
            if (Managers.get(user).getfire())
                return Managers.remove(user) != null && CheckTegrati_HaveOwners();

        }
        if (Owners.containsKey(user)) {
            if (Owners.get(user).getfire())
                return Owners.remove(user) != null && CheckTegrati_HaveOwners();
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
        return Managers.put(worker.getName(), worker) != null && CheckTegrati_HaveOwners();
    }

    @Override
    public boolean appointOwner(StoreOwner_Imp worker) {
        if (Managers.containsKey(worker.getName()) || Owners.containsKey(worker.getName())) {
            ErrorLogger.GetInstance().Add_Log(this.toString() + "cant have 2 workers with same names");
            return false;
        }
        EventLogger.GetInstance().Add_Log(this.toString() + "- appoint new manager in store");
        return Owners.put(worker.getName(), worker) != null && CheckTegrati_HaveOwners();
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

//		if (!p.getStore().getName().equals(name)) {
//			ErrorLogger.GetInstance().Add_Log(this.toString() + "add product - product store not currect");
//
//			return false;
//		}
        EventLogger.GetInstance().Add_Log(this.toString() + "-add product");

        return inventory.recive_item(new Packet_Of_Prodacts(p));
    }

    public boolean addProduct(ProductDetails p) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-add product");

        return inventory.recive_item(p);
    }

    public Product findProductByName(String name) {
        //TODO this needs to return ProdcutDetails
        return inventory.getItem(name);

    }

    public ProductDetails findProductDetailsByName(String name) {
        //TODO change this
        return inventory.findProductDetailsByName(name, this.name);
    }

    public List<Product> findProductByCategory(String category) {
        return inventory.findProductByCategory(category);

    }

    public List<ProductDetails> findProductDetailsByCategory(String category) {
        //TODO change this
        return ProductDetails.adapteProdactList(inventory.findProductByCategory(category), name);

    }

    public List<Product> findProductByKeyword(String keyword) {
        return inventory.findProductByKeyword(keyword);

    }

    public List<ProductDetails> findProductDetailsByKeyword(String keyword) {
        return ProductDetails.adapteProdactList(inventory.findProductByKeyword(keyword), name);

    }

    public Boolean CheckItemAvailable(ProductDetails item) {
        if (findProductByName(item.getName()) == null)
            return false;
        if (findProductByName(item.getName()).getAmount() > item.getAmount())
            return true;

        return false;
    }

    public boolean CheckAcquisitions(List<ProductDetails> products) {
        return acquisitions.canPurchase(products);
    }

// ----------------------------------------------------buying

    public double getPrice(List<ProductDetails> items) {

        for (ProductDetails PD : items) {
            if (PD.getStoreName() != this.name)
                ErrorLogger.GetInstance().Add_Log(this.toString() + "- calculating price for product in wrong store");
        }
        return discounts.applyDiscounts(items);
    }

    @Override
    synchronized public MyPair<Product, String> TakeItem(String name, int amount) {
        MyPair<Product, String> takeout = null;
        Product temp = findProductByName(name);
        if (temp == null) {
            ErrorLogger.GetInstance().Add_Log(this.toString() + "-takeitem cant find proudct");
            return null;
        }
        if (temp.getAmount() > amount) {
            EventLogger.GetInstance().Add_Log(this.toString() + "- taking out products full amount");
            takeout = new MyPair<>(new Product(name, temp.getCategory(), temp.getKeyWords(), temp.getPrice(), amount),
                    this.name);
            temp.removeAmount(amount);
        } else {
            //TODO maybe cancell buy
            EventLogger.GetInstance().Add_Log(this.toString() + "- taking out products not full amount");
            takeout = new MyPair<>(new Product(name, temp.getCategory(), temp.getKeyWords(), temp.getPrice(), temp.getAmount()),
                    this.name);

            temp.removeAmount(temp.getAmount());
        }
        return takeout;
    }

    public boolean addPurchase(StorePurchase p) {
        purchaseHistory.PastPurchase.add(p);
        return true;
    }

    public String getDiscounts(String name) {
        return discounts.toString();

    }

    // ----------------------------------------------------discount
    public boolean addDiscount(String discount) {
        return discounts.addDiscount(discount);
    }

    public boolean removeDiscount(int discountID) {
        return discounts.removeDiscount(discountID);
    }


    // --------------------------------------------------- questions
    public Collection<Question> getQuestions() {

        return questions.values();
    }

    public boolean respondToQuestion(String ansewer, int qustionID) {
        questions.get(qustionID).addAnsewers(ansewer);
        return true;

    }

    boolean CheckTegrati_HaveOwners() {
        //  return true;
        return creator != null || Owners.values().size() > 0;
    }


    public boolean addacquisition(String acquisition) {
        return acquisitions.addAcquisitionPolicy(acquisition);
    }

    public boolean removeacquisition(int acquisitionID) {
        return acquisitions.removeAcquisition(acquisitionID);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreImp storeImp = (StoreImp) o;
        return //getRating() == storeImp.getRating() &&
                Objects.equals(getName(), storeImp.getName()) //&&
                /*Objects.equals(getCreator(), storeImp.getCreator()) &&
                Objects.equals(inventory, storeImp.inventory) &&
                Objects.equals(getOwners(), storeImp.getOwners()) &&
                Objects.equals(getManagers(), storeImp.getManagers()) &&
                Objects.equals(getAddress(), storeImp.getAddress()) &&
                Objects.equals(discounts, storeImp.discounts) &&
                Objects.equals(acquisitions, storeImp.acquisitions) &&
                Objects.equals(purchaseHistory, storeImp.purchaseHistory) &&
                Objects.equals(getQuestions(), storeImp.getQuestions())*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCreator(), inventory, getOwners(), getManagers(), getAddress(), getRating(), discounts, acquisitions, purchaseHistory, getQuestions());
    }
}
