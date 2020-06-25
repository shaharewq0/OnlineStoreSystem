package Domain.Store;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.Policies.Acquisitions.Acquisition;
import Domain.Policies.Acquisitions.AcquisitionPolicy;
import Domain.Policies.Discounts.Discount;
import Domain.Policies.Discounts.DiscountPolicy;
import Domain.Store.workers.Creator;
import Domain.Store.workers.StoreManager_Imp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.appoints.Appoint_Owner;
import Domain.Store.workers.appoints.Appoint_manager;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import extornal.supply.Packet_Of_Prodacts;

import java.util.*;

public class StoreImp implements IStore {
    private int id;
    private String name;
    private Creator creator;
    private Store_Inventory inventory = new Store_Inventory();
    private Map<String, Appoint_Owner> Owners = new HashMap<String, Appoint_Owner>();
    private Map<String, Appoint_manager> Managers = new HashMap<String, Appoint_manager>();
    private String address;
    private int rating;
    private DiscountPolicy discounts = new DiscountPolicy();
    private AcquisitionPolicy acquisitions = new AcquisitionPolicy();

    private Store_Purchase_History purchaseHistory = new Store_Purchase_History();
    private Map<Integer, Question> questions = new HashMap<Integer, Question>();

    public StoreImp(String name, Collection<Product_boundle> products, String address, int rating) {

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

    public StoreImp(){}

    public void myCreator(Creator c) {
        creator = c;
    }

    // ----------------------------------------------------------------------------------------my
    // info

    @Override
    public int getId() {
        return id;
    }

    public AcquisitionPolicy getAcquisitions() {
        return acquisitions;
    }

    public DiscountPolicy getDiscounts() {
        return discounts;
    }

    public Store_Inventory getInventory() {
        return inventory;
    }

    public Store_Purchase_History getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setAcquisitions(AcquisitionPolicy acquisitions) {
        this.acquisitions = acquisitions;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setDiscounts(DiscountPolicy discounts) {
        this.discounts = discounts;
    }

    public void setInventory(Store_Inventory inventory) {
        this.inventory = inventory;
    }

    public void setManagers(Map<String, Appoint_manager> managers) {
        Managers = managers;
    }

    public void setOwners(Map<String, Appoint_Owner> owners) {
        Owners = owners;
    }

    public void setPurchaseHistory(Store_Purchase_History purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public void setQuestions(Map<Integer, Question> questions) {
        this.questions = questions;
    }


    public Creator getCreator() {
        return creator;
    }

    public String getName() {
        return name;
    }

    public Collection<Product> getProducts() {
        List<Product> output = new LinkedList();
        for (Product_boundle PB : inventory.items.values()) {
            output.add(PB.item);
        }
        return output;
    }

    public List<ProductDetails> getProductsDetails() {
        return ProductDetails.adapteProdactList(inventory.items.values(), name);
    }

    public ProductDetails getProductDetails(String productName) {
        List<ProductDetails> all = getProductsDetails();

        for (ProductDetails prod: all) {
            if(prod.getName().equals(productName)){
                return prod;
            }
        }

        return null;
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
        Collection<StoreOwner_Imp> MyOwners = new LinkedHashSet<>();
        for (Appoint_Owner AO : Owners.values()) {
            MyOwners.add(AO.grantee);
        }
        return MyOwners;
    }

    public Collection<StoreManager_Imp> getManagers() {
        Collection<StoreManager_Imp> MyManagers = new LinkedHashSet<>();
        for (Appoint_manager AM : Managers.values()) {
            MyManagers.add(AM.grantee);
        }
        return MyManagers;
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
            if (Managers.get(user).grantee.getfire())
                return Managers.remove(user) != null && CheckTegrati_HaveOwners();

        }
        if (Owners.containsKey(user)) {
            if (Owners.get(user).grantor.getfire())
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
    public boolean appointManager(Appoint_manager worker) {
        if (Managers.containsKey(worker.grantee.getName()) || Owners.containsKey(worker.grantee.getName())) {
            ErrorLogger.GetInstance().Add_Log(this.toString() + "cant have 2 workers with same names");
            return false;
        }
        EventLogger.GetInstance().Add_Log(this.toString() + "- appoint new manager in store");
        return Managers.put(worker.grantee.getName(), worker) != null && CheckTegrati_HaveOwners();
    }

    @Override
    public boolean appointOwner(Appoint_Owner worker) {
        if (Managers.containsKey(worker.grantee.getName()) || Owners.containsKey(worker.grantee.getName())) {
            ErrorLogger.GetInstance().Add_Log(this.toString() + "cant have 2 workers with same names");
            return false;
        }
        EventLogger.GetInstance().Add_Log(this.toString() + "- appoint new manager in store");
        return Owners.put(worker.grantee.getName(), worker) != null && CheckTegrati_HaveOwners();
    }

    public boolean editManagerPermesions(String managername, List<String> permesions) {
        if (!Managers.containsKey(managername))
            return false;
        StoreManager_Imp m = Managers.get(managername).grantee;
        if (m != null) {
            EventLogger.GetInstance().Add_Log(this.toString() + "- edit manager permesions");
            return m.getNewPermesions(permesions);
        }
        ErrorLogger.GetInstance().Add_Log(this.toString() + "edit manager manager dont exsist");

        return false;
    }

//-------------------------------------------------------------------------- products --	

    @Override
    public boolean editProduct(String OLD_p, ProductDetails NEW_p) {
        EventLogger.GetInstance().Add_Log(this.toString() + "- edit item");

        return inventory.editProduct(OLD_p, NEW_p);
    }

    @Override
    public boolean removeProduct(String pName) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-remove product");

        return inventory.removeProduct(pName);
    }

    public boolean addProduct(Product p, int amount) {

        EventLogger.GetInstance().Add_Log(this.toString() + "-add product");

        return inventory.recive_item(new Packet_Of_Prodacts(new Product_boundle(p, amount)));
    }

    public boolean addProduct_bundle(Product_boundle PB) {

        EventLogger.GetInstance().Add_Log(this.toString() + "-add product Bundle");

        return inventory.recive_item(new Packet_Of_Prodacts(PB));
    }

    public boolean addProduct(ProductDetails p) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-add product");

        return inventory.recive_item(p);
    }

    public Product findProductByName(String name) {
        //TODO this needs to return ProdcutDetails
        return inventory.getItem(name);

    }

    public Product_boundle findProduct_bundleByName(String name) {
        //TODO this needs to return ProdcutDetails
        return inventory.getBundleItem(name);

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
        return ProductDetails.adapteProdactList(inventory.findProduct_BundleByCategory(category), name);

    }

    public List<Product> findProductByKeyword(String keyword) {
        return inventory.findProductByKeyword(keyword);

    }

    public List<ProductDetails> findProductDetailsByKeyword(String keyword) {
        return ProductDetails.adapteProdactList(inventory.findProduct_BundleByKeyword(keyword), name);

    }

    public Boolean CheckItemAvailable(ProductDetails item) {
        if (findProductByName(item.getName()) == null)
            return false;
        if (inventory.items.get(item.getName()).size() > item.getAmount())
            return true;

        return false;
    }


    public boolean CheckAcquisitions(List<Product_boundle> products) {
        return acquisitions.canPurchase(products);
    }

// ----------------------------------------------------buying

    public double getPrice(List<Product_boundle> items) {
        return discounts.applyDiscounts(items);
    }

    @Override
    synchronized public MyPair<Product_boundle, String> TakeItem(String name, int amount) {
        MyPair<Product_boundle, String> takeout = null;
        Product_boundle temp = inventory.items.get(name);
        if (temp == null) {
            ErrorLogger.GetInstance().Add_Log(this.toString() + "-takeitem cant find proudct");
            return null;
        }
        if (temp.size() > amount) {
            EventLogger.GetInstance().Add_Log(this.toString() + "- taking out products full amount");
            takeout = new MyPair<>(new Product_boundle(new Product(name, temp.item.getCategory(), temp.item.getKeyWords(), temp.item.getPrice(), temp.item.getRating()), amount
            ), this.name);
            temp.remove(amount);
        } else {
            //TODO maybe cancell buy
            EventLogger.GetInstance().Add_Log(this.toString() + "- taking out products not full amount");
            takeout = new MyPair<>(new Product_boundle(new Product(name, temp.item.getCategory(), temp.item.getKeyWords(), temp.item.getPrice(), temp.item.getRating()), temp.size()),
                    this.name);

            temp.remove(temp.size());
        }
        return takeout;
    }

    public boolean addPurchase(StorePurchase p) {
        purchaseHistory.PastPurchase.add(p);
        return true;
    }

    public String getDiscountsString() {
        return discounts.toString();

    }

    public String getAcquisitionsString() {
        return acquisitions.toString();
    }

    // ----------------------------------------------------discount
    public boolean addDiscount(Discount discount) {
        List<String> ls = discount.getProductsNames();
        List<Product> items = new LinkedList<>();
        for (String name : ls) {
            Product p = inventory.getItem(name);
            if (p != null)
                items.add(0,p);
            if(name.equals("ALL"))
                items.add(0,null);
        }
        discount.replaceProducts(items);
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
        questions.get(qustionID).addAnswers(ansewer);
        return true;

    }

    boolean CheckTegrati_HaveOwners() {
        return creator != null || Owners.values().size() > 0;
    }



    public boolean addacquisition(Acquisition acquisition) {

        List<String> ls = acquisition.getProductsNames();
        List<Product> items = new LinkedList<>();
        for (String name : ls) {
            Product p = inventory.getItem(name);
            if (p != null)
                items.add(0,p);
            if(name.equals("ALL"))
                items.add(0,null);
        }
        acquisition.replaceProducts(items);
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
