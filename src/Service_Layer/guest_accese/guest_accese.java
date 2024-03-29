package Service_Layer.guest_accese;

import Domain.Store.MyPair;
import Domain.Store.Product_boundle;
import Domain.UserClasses.User;
import Domain.info.ProductDetails;
import Domain.info.StoreInfo;
import Domain.store_System.ClintObserver;
import Domain.store_System.System;
import Service_Layer.userAddress;
import extornal.EternalProxy;
import extornal.payment.CreditCard;
import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.inventory;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class guest_accese {


    public static Queue<Integer> failedPyments = new ConcurrentLinkedQueue<>();

    // when a guesset is online for the first time he needs to call this function to
    // get his guest token
    public static int ImNew() {
        return System.getInstance().ImNew();
    }

    static public ProductDetails searchProductByName(String name, String store) {
        return User.searchProductByName(name, store);
    }

    public static boolean usecase2_3_login(int guestId, String username, String password) {
        //TODO maybe allow this
        //return false; // fucntion no longer optional. need to get observer now
        User user = System.getInstance().getGuest(guestId);
        if (user == null)
            return false;
        return user.login(username, password);
    }

    public static boolean usecase2_3_login(int guestId, String username, String password, ClintObserver Observer) {
        User user = System.getInstance().getGuest(guestId);
        if (user == null)
            return false;
        return user.login(username, password, Observer);
    }


    public static boolean usecase2_2_guest_register(String username, String password) {
        return User.register(username, password);
        // return System.getInstance().register(username, password);
    }

    public static StoreDetails usecase2_4A_getStoreDetails(String storeName) {
        return User.watchStoreDetails(storeName);

    }

    public static StoreInfo usecase2_4B_getStoreProdacts(String storeName) {
        return User.watchStoreInfo(storeName);
    }

    public static List<ProductDetails> usecase2_5A_searchProductByName(String name) {
        return User.searchProductsByName(name);
    }

    public static List<ProductDetails> usecase2_5B_searchProductByCategory(String category) {
        return User.searchProductsByCategory(category);
    }

    public static List<ProductDetails> usecase2_5C_searchProductByKeyword(String keyword) {
        return User.searchProductsByKeyword(keyword);
    }

    public static List<ProductDetails> usecase2_5D_1_FilterbyPrice(double minPrice, double maxPrice) {
        return User.filterByPrice(minPrice, maxPrice);
    }

    public static List<ProductDetails> usecase2_5D_2_FilterbyRating(int minRating, int maxRating) {
        return User.filterByRating(minRating, maxRating);
    }

    public static List<ProductDetails> usecase2_5D_3_FilterbyCategory(String category) {
        return User.filterByCategory(category);

    }

    public static ProductDetails getProductDetails(String store, String productName) {
        return System.getInstance().getStoreDetails(store).getProductDetails(productName);
    }

    public static List<ProductDetails> usecase2_5D_4_FilterbyStoreRating(int minRating, int maxRating, List<ProductDetails> prods) {
        return User.filterByStoreRating(minRating, maxRating, prods);
    }

    public static List<ProductDetails> usecase2_5D_1_FilterbyPrice(double minPrice, double maxPrice, List<ProductDetails> prods) {
        return User.filterByPrice(minPrice, maxPrice, prods);
    }

    public static List<ProductDetails> usecase2_5D_2_FilterbyRating(int minRating, int maxRating, List<ProductDetails> prods) {
        return User.filterByRating(minRating, maxRating, prods);
    }

    public static List<ProductDetails> usecase2_5D_3_FilterbyCategory(String category, List<ProductDetails> prods) {
        return User.filterByCategory(category, prods);

    }

    public static List<ProductDetails> usecase2_5D_4_FilterbyStoreRating(int minRating, int maxRating) {
        return User.filterByStoreRating(minRating, maxRating);
    }

    // need guest ID
    public static boolean usecase2_6_saveProductToBasket(int guestID, String storename, String prodactname, int amount) {

        User user = System.getInstance().getGuest(guestID);
        if (user == null)
            return false;

        StoreDetails store = guest_accese.usecase2_4A_getStoreDetails(storename);
        ProductDetails product = guest_accese.searchProductByName(prodactname, storename);

        if (store == null)
            return false;

        if (product == null)
            return false;

        if (product.getAmount() < amount)
            return false;

        return user.saveProductInBasket(prodactname, storename, amount);
    }


    public static List<ProductDetails> usecase2_7A_WatchProdactsInCart(int guestID) {
        User user = System.getInstance().getGuest(guestID);
        if (user == null)
            return null;
        return user.getProductsInCart();
    }

    public static double getCartPrice(int guestID) {
        User user = System.getInstance().getGuest(guestID);
        if (user == null)
            return -1;
        return user.getCartPrice();
    }

    public static int usecase2_7b_RemoveProdactsInCart(int guestID, String storename, String prodactname, int amount) {
        User user = System.getInstance().getGuest(guestID);
        if (user == null)
            return -1;
        return user.deleteProductInBasket(prodactname, storename, amount);
    }

    //2.8
    public static boolean usecase2_8_Purchase_products(int guestID, CreditCard card, userAddress whereToSend) {

        if (!EternalProxy.getInstance().handshake()) {
            return false; // no external systems!
        }

        if (!RefundAll()) {
            return false; // there are stil unrefunded castomer, until external system return, no new purchases can be made!
        }

        if (!usecase2_8_1_Check_available_products(guestID))
            return false;


        List<MyPair<Product_boundle, String>> items = usecase2_8_5_Update_inventory(guestID);
        double price = usecase2_8_2_Calculate_price(guestID);
        int paymantID = System.getInstance().navigatePayment().pay(card, price);
        //Tals code	//int paymant = EternalProxy.getInstance().pay(card.getCardNumber(), card.getExpirationDate(), card.getCardOwner(), card.getCSS(), card.getOwnerID()); //boolean didPay = System.getInstance().navigatePayment().pay(bank, -price);
        if (paymantID < 0) {
            usecase2_8_3_ReturnProdoctsToStore(items);
            return false;
        }
        Packet_Of_Prodacts pack = new Packet_Of_Prodacts();
        pack.add_items(items);
        int supplyID = System.getInstance().navigateSupply().order(pack, whereToSend);
        //tals code int suply = EternalProxy.getInstance().supply(whereToSend.getCountry(), whereToSend.getCity(), whereToSend.getAddress(), whereToSend.getZipcode(), whereToSend.getReciver()); //boolean didSupplay = System.getInstance().navigateSupply().order(pack, whereToSend);
        if (supplyID < 0) {
            usecase2_8_3_ReturnProdoctsToStore(items);
            usecase2_8_4_Guest_Refund(card, price, paymantID);
            return false;
        }
        System.getInstance().getGuest(guestID).Complet_Purchase(price);
        return true;
    }

    public static boolean usecase2_8_1_Check_available_products(int guestID) {
        User user = System.getInstance().getGuest(guestID);
        if (user == null)
            return false;
        //TODO this is wrong
        return user.CheckItemAvailableA() && user.CheckAcquisitions();
        //User.CheckAvilibleItems();
        //return System.getInstance().CheckItemAvailableA(System.getInstance().getGuest(guestID).getProductsInCart());
    }

    public static double usecase2_8_2_Calculate_price(int guestID) {
        User user = System.getInstance().getGuest(guestID);
        return user.getCart().CalcPrice();
    }

    public static boolean usecase2_8_3_ReturnProdoctsToStore(List<MyPair<Product_boundle, String>> products) {
        return System.getInstance().fillStore(products);
    }

    public static boolean usecase2_8_4_Guest_Refund(CreditCard cardnumber, double amount, int transactionID) {
        return usecase2_8_4_Guest_Refund(transactionID);
    }

    public static boolean usecase2_8_4_Guest_Refund(int transactionID) {
        boolean success = System.getInstance().navigatePayment().cancel_pay(transactionID);
        if (!success) {
            if (!failedPyments.contains(transactionID)) {
                failedPyments.add(transactionID);
            }
        }
        return success;
    }

    public static boolean RefundAll() {
        if (failedPyments.isEmpty()) {
            return true;
        }
        while (!failedPyments.isEmpty()) {
            int transactionID = failedPyments.poll();
            if (!usecase2_8_4_Guest_Refund(transactionID)) {
                failedPyments.add(transactionID);
                return false;
            }
        }
        return true;
    }

    public static List<MyPair<Product_boundle, String>> usecase2_8_5_Update_inventory(int guestID) {
        User user = System.getInstance().getGuest(guestID);
        if (user == null)
            return null;
        return user.getCart().getItems();
    }

}
