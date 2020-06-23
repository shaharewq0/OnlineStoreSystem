package Domain.UserClasses;

import Domain.Logs.EventLogger;
import Domain.Policies.Acquisitions.Acquisition;
import Domain.Policies.Discounts.Discount;
import Domain.Store.Product;
import Domain.Store.Product_boundle;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.Store.workers.Creator;
import Domain.Store.workers.Store_role;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.info.StoreInfo;
import Domain.store_System.ClintObserver;
import Domain.store_System.Roles.Member;
import Domain.store_System.Roles.Registered;
import Domain.store_System.Roles.System_Manager;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class User implements IUser {

    private shoppingCart cart;
    // imps of new classs digram
    private Registered profile = null;
    private Member logInstanse = null;
    private System_Manager sysMangaer = null;
    // ---- dont need to be here

    // TODO move this to register and call it from member system role

    public User() {
        cart = new shoppingCart();
    }

    //delte this
    public User(String address, int creditCardNum) {
        cart = new shoppingCart();
    }

    // ------------------------------ user
    public boolean login(String id, String password, ClintObserver CO) {
        if (profile != null)
            return false;


        profile = System.getInstance().login(id, password, this);
        logInstanse = System.getInstance().getLogInstase(id, password);
        sysMangaer = System.getInstance().ImManeger(id, password);
        profile.LogLogin(this, CO);

        return profile != null;

    }

    public boolean login(String id, String password) {
        if (profile != null)
            return false;
        profile = System.getInstance().login(id, password, this);
        logInstanse = System.getInstance().getLogInstase(id, password);
        sysMangaer = System.getInstance().ImManeger(id, password);

        return profile != null;

    }

    // TODO rewrite this fucntion
    // adding a product to a basket. if the product exists add 1 to the amount of
    // the product in the basket
    public boolean saveProductInBasket(String productName, String storeName, int amount) {
        shoppingBasket basket = cart.findBasket(storeName);
        if (basket == null)
            return false;
        return basket.addProduct(productName, amount);
        //return true;

    }

    // removing at most amount of num of a product from the basket
    public int deleteProductInBasket(String productName, String storeName, int num) {
        EventLogger.GetInstance().Add_Log(this.toString() + "- remove item from cart");
        return cart.removeItem(storeName, productName, num);

    }

    public List<ProductDetails> getProductsInCart() {
        return cart.allProductsInCart();
    }

    public boolean logout() {
        if (profile == null || logInstanse == null)
            return false;
        profile.LogLogout(this);
        profile = null;
        logInstanse = null;
        System.getInstance().logout(this);

        return true;
//		if (system_role instanceof Member) {
//			system_role = new Guest();
//			return true;
//		}
//		return false;
    }

    public boolean openStore(String storename, String address, int rating) {

       // Notifier.getInstance().update("abc", "abc" +" openning a store! (From server)");

        StoreImp mystore = logInstanse.OpenStore(new StoreInfo(storename, address, rating));
        if (profile == null) {
            EventLogger.GetInstance().Add_Log(this.toString() + "- trying to open store while not login");
            return false;
        }
        EventLogger.GetInstance().Add_Log(this.toString() + "- opening store");
        profile.store_roles.put(mystore.getName(), new Creator(mystore,profile));

        return mystore != null;
    }

    public boolean openStore(StoreInfo store) {

        StoreImp mystore = logInstanse.OpenStore(store);
        if (profile == null || mystore == null) {
            EventLogger.GetInstance().Add_Log(this.toString() + "- trying to open store while not login");
            return false;
        }
        EventLogger.GetInstance().Add_Log(this.toString() + "- opening store");

        profile.store_roles.put(mystore.getName(), new Creator(mystore,profile));
        return mystore != null;

    }

    public List<UserPurchase> getPurchaseHistory() {
        if (profile == null)
            return null;

        return profile.getPurchesHistory();
    }

    public void Complet_Purchase(double price) {
        UserPurchase purchase = cart.Complet_Purchase();
        purchase.TotalePrice = price;
        if (profile != null) {
            profile.getPurchesHistory().add(purchase);
        }

    }

    // -------------------------------------------------- store info

    public shoppingCart getCart() {
        return cart;
    }

    public Collection<ProductDetails> watchProductsInStore(String name) {
        return System.getInstance().getProductsFromStore(name);
    }
    // --------------------------------------------------------------- store actions

    public boolean addProduct(String storeName, Product_boundle p) {
//TODO add fail
        if (profile == null)
            return false;
        Store_role role = profile.store_roles.get(storeName);
        if (role == null)
            return false;
        return role.addItem(p);
    }

    public boolean editProduct(String storeName, String prodactname, Product newdetail) {
        if (profile == null)
            return false;
        Store_role role = profile.store_roles.get(storeName);
        if (role == null)
            return false;
        return role.editItem(prodactname, newdetail);
    }

    public boolean removeProduct(String storeName, String prodactname) {

        if (profile == null)
            return false;
        Store_role role = profile.store_roles.get(storeName);
        if (role == null)
            return false;
        return role.removeItem(prodactname);
    }

    //	public boolean appointOwner(String storeName, String username, String otherPassword) {
//		if (profile == null)
//			return false;
//		User appointee = System.getInstance().getMember(username, otherPassword);
//		if(appointee == null)
//			return false;
//		return profile.store_roles.get(storeName).appointOwner(appointee);
//	}
    public boolean appointOwner(String storeName, String hisusername) {
        if (profile == null)
            return false;
        Registered appointee = System.getInstance().getUserProfile(hisusername);
        if (appointee == null)
            return false;
        return profile.store_roles.get(storeName).appointOwner(appointee);
    }

    @Override
    public boolean appointAsOwner(Store_role role) {
        if (profile == null || profile.store_roles.containsKey(role.getStore().getName())
                && !profile.store_roles.get(role.getStore().getName()).canPromoteToOwner()) {
            return false;
        }
        profile.store_roles.remove(role.getStore().getName());
        profile.store_roles.put(role.getStore().getName(), role);
        return true;

    }

    public boolean appointManager(String storeName, String username) {

        if (profile == null)
            return false;
        Registered appointee = System.getInstance().getUserProfile(username);
        if (appointee == null)
            return false;
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.get(storeName) == null)
            return false;
        return store_roles.get(storeName).appointManager(appointee);

    }

    @Override
    public boolean appointAsManager(Store_role role) {
        // TODO this is the same as appoint owner
        if (profile == null)
            return false;
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.containsKey(role.getStore().getName())
                && !store_roles.get(role.getStore().getName()).canPromoteToOwner()) {
            return false;
        }
        store_roles.remove(role.getStore().getName());
        store_roles.put(role.getStore().getName(), role);
        return true;
    }

    public boolean fireManager(String storeName, String username) {
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.get(storeName) == null)
            return false;
        return store_roles.get(storeName).fireManager(username);
    }

    @Override
    public boolean getFired(String store) {
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.get(store) == null)
            return false;
        return store_roles.remove(store) != null;
    }

    public boolean addDiscount(Discount discount, String store) {
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.get(store) == null)
            return false;
        return store_roles.get(store).addDiscount(discount);
    }

    public boolean removeDiscount(int discountID, String store) {
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.get(store) == null)
            return false;
        return store_roles.get(store).removeDiscount(discountID);
    }

    public boolean addacquisition(Acquisition acquisition, String storeName) {
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.get(storeName) == null)
            return false;
        return store_roles.get(storeName).addacquisition(acquisition);
    }

    public boolean removeacquisition(int acquisitionID, String storeName) {
        Map<String, Store_role> store_roles = profile.store_roles;
        if (store_roles.get(storeName) == null)
            return false;
        return store_roles.get(storeName).removeacquisition(acquisitionID);
    }

    private String last_store_looked_at = "";

    public Collection<Question> viewQuestions(String storeName) {
        last_store_looked_at = storeName;
        Map<String, Store_role> store_roles = profile.store_roles;
        return store_roles.get(storeName).viewQuestions();
    }

    public boolean giveRespond(String ansewr, int qustionID) {
        Map<String, Store_role> store_roles = profile.store_roles;
        return store_roles.get(last_store_looked_at).giveRespond(ansewr, qustionID);
    }

    public List<StorePurchase> ViewAquistionHistory(String storeName) {
        if (sysMangaer != null)
            return sysMangaer.getPurchaseHistory(storeName);
        Map<String, Store_role> store_roles = profile.store_roles;
        return store_roles.get(storeName).getPurchaseHistory();
    }

    public List<UserPurchase> ViewAquistionHistoryOfUser(String username) {
        if (sysMangaer != null)
            return sysMangaer.getPurchaseHistoryofUser(username);
        return null;
    }

    public boolean editManagerPermesions(String storename, String managername, List<String> permesions) {
        Map<String, Store_role> store_roles = profile.store_roles;
        EventLogger.GetInstance().Add_Log(this.toString() + "- editing managerPermesions");
        return store_roles.get(storename).editManagerPermesions(managername, permesions);
    }


    // Static -
    // ------------------------------------------------------------------------------------------------------------------

    static public boolean register(String id, String password) {
        return System.getInstance().register(id, password);
    }

    static public StoreDetails watchStoreDetails(String name) {
        StoreImp store = System.getInstance().getStoreDetails(name);
        if (store == null)
            return null;
        return new StoreDetails(store);
    }

    static public StoreInfo watchStoreInfo(String storeName) {
        StoreImp store = System.getInstance().getStoreDetails(storeName);
        if (store == null)
            return null;
        return store.getMyInfo();
    }

    static public List<ProductDetails> searchProductsByName(String name) {
        return System.getInstance().searchProductsByName(name);
    }

    static public ProductDetails searchProductByName(String name, String store) {
        return System.getInstance().searchProductByName(name, store);
    }

    static public List<ProductDetails> searchProductsByCategory(String category) {
        return System.getInstance().searchProductsByCategory(category);
    }

    static public List<ProductDetails> searchProductsByKeyword(String keyword) {
        return System.getInstance().searchProductsByKeyword(keyword);
    }

    static public List<ProductDetails> filterByPrice(double minPrice, double maxPrice) {

        List<ProductDetails> output = new LinkedList<ProductDetails>();
        for (StoreImp store : System.getInstance().getAllStores()) {
            for (ProductDetails product : store.getProductsDetails()) {
                if (product.getPrice() <= maxPrice && product.getPrice() >= minPrice)
                    output.add(product);
            }
        }

        return output;
    }

    static public List<ProductDetails> filterByRating(int minRating, int maxRating) {
        List<ProductDetails> output = new LinkedList<ProductDetails>();
        for (StoreImp store : System.getInstance().getAllStores()) {
            for (ProductDetails product : store.getProductsDetails()) {
                if (product.getRating() <= maxRating && product.getRating() >= minRating)
                    output.add(product);
            }
        }
        return output;
        // return System.getInstance().filterByRating( min, max);
    }

    static public List<ProductDetails> filterByCategory(String category) {
        List<ProductDetails> output = new LinkedList<ProductDetails>();
        for (StoreImp store : System.getInstance().getAllStores()) {
            for (ProductDetails product : store.getProductsDetails()) {
                if (product.getCategory().contains(category))
                    output.add(product);
            }
        }
        return output;
        // return System.getInstance().filterByCategory(category);
    }

    static public List<ProductDetails> filterByStoreRating(int minRating, int maxRating) {
        List<ProductDetails> output = new LinkedList<ProductDetails>();
        for (StoreImp store : System.getInstance().getAllStores()) {
            if (store.getRating() > minRating && store.getRating() < maxRating)
                for (ProductDetails product : store.getProductsDetails()) {
                    output.add(product);
                }
        }
        return output;
        // return System.getInstance().filterByStoreRating( min, max);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        if (profile == null)
            return "Error still no name";
        return profile.getId();
    }

    public List<String> storeOwned() {
        if (profile != null)
            return profile.storesOwned();

        return new LinkedList<>();
    }

    public List<String> roles() {
        if (profile != null)
            return profile.roles();

        return new LinkedList<>();
    }


    public boolean CheckItemAvailableA() {
        return cart.CheckItemAvailable();

    }

    public boolean CheckAcquisitions() {
        return cart.CheckAcquisitions();
    }


}
