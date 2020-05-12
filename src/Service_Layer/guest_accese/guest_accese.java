package Service_Layer.guest_accese;

import java.util.List;

import Domain.RedClasses.User;
import Domain.Store.Product;
import Domain.info.ProductDetails;
import Domain.info.StoreInfo;
import Domain.store_System.System;
import extornal.payment.bankAccount;
import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.inventory;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class guest_accese {

	// when a guesset is online for the first time he needs to call this function to
	// get his guest token
	public int ImNew() {
		return System.getInstance().ImNew();
	}

//	//TODO old need to delete
//	public boolean usecase2_3_login(String username, String password) {
//		//TODO old need to delete
//		return System.getInstance().login(username, password) != null;
//	}
	
	public boolean usecase2_3_login(int guestId, String username, String password) {
		return System.getInstance().getGuest(guestId).login(username, password);
	}

	public boolean usecase2_2_guest_register(String username, String password) {
		return User.register(username, password);
		// return System.getInstance().register(username, password);
	}

	public StoreDetails usecase2_4A_getStoreDetails(String storeName) {
		return User.watchStoreDetails(storeName);

	}

	public StoreInfo usecase2_4B_getStoreProdacts(String storeName) {
		return User.watchStoreInfo(storeName);
	}

	public List<ProductDetails> usecase2_5A_searchProductByName(String name) {
		return User.searchProductsByName(name);
	}

	public List<ProductDetails> usecase2_5B_searchProductByCategory(String category) {
		return User.searchProductsByCategory(category);
	}

	public List<ProductDetails> usecase2_5C_searchProductByKeyword(String keyword) {
		return User.searchProductsByKeyword(keyword);
	}

	public List<ProductDetails> usecase2_5D_1_FilterbyPrice(int minPrice, int maxPrice) {
		return User.filterByPrice(minPrice, maxPrice);
	}

	public List<ProductDetails> usecase2_5D_2_FilterbyRating(int minRating, int maxRating) {
		return User.filterByRating(minRating, maxRating);
	}

	public List<ProductDetails> usecase2_5D_3_FilterbyCategory(List<String> prodacts, String category) {
		return User.filterByCategory(category);

	}

	public List<ProductDetails> usecase2_5D_4_FilterbyStoreRating(int minRating, int maxRating) {
		return User.filterByStoreRating(minRating, maxRating);
	}

	// need guest ID
	public boolean usecase2_6_saveProductToBasket(int guestID, String storename, String prodactname, int amount) {

		return System.getInstance().getGuest(guestID).saveProductInBasket(prodactname, storename,amount);
	}

	
	public List<ProductDetails> usecase2_7A_WatchProdactsInCart(int guestID) {
		return System.getInstance().getGuest(guestID).getProductsInCart();
	}

	public int usecase2_7b_RemoveProdactsInCart(int guestID, String storename, String prodactname, int amount) {
		return System.getInstance().getGuest(guestID).deleteProductInBasket(prodactname, storename, amount);
	}

	//2.8
	public boolean usecase2_8_Purchase_products(int guestID, bankAccount bank, inventory whereToSend) {
		if (!usecase2_8_1_Check_available_products(guestID))
			return false;
		List<Product> items = usecase2_8_5_Update_inventory(guestID);
		double price = usecase2_8_2_Calculate_price(guestID);
		boolean didPay = System.getInstance().navigatePayment().pay(bank, -price);
		if (!didPay) {
			usecase2_8_3_ReturnProdoctsToStore(items);
			return false;
		}
		Packet_Of_Prodacts pack = new Packet_Of_Prodacts();
		pack.items.addAll(items);
		boolean didSupplay = System.getInstance().navigateSupply().order(pack, whereToSend);
		if (!didSupplay) {
			usecase2_8_3_ReturnProdoctsToStore(items);
			usecase2_8_4_Guest_Refund(bank, price);
			return false;
		}
		System.getInstance().getGuest(guestID).Complet_Purchase(price);
		return true;
	}

	public boolean usecase2_8_1_Check_available_products(int guestID) {
		return System.getInstance().CheckItemAvailableA(System.getInstance().getGuest(guestID).getProductsInCart());
	}

	public double usecase2_8_2_Calculate_price(int guestID) {
		return System.getInstance().getGuest(guestID).getCart().CalcPrice();
	}

	public boolean usecase2_8_3_ReturnProdoctsToStore(List<Product> products) {
		return System.getInstance().fillStore(products);
	}

	public boolean usecase2_8_4_Guest_Refund(bankAccount cardnumber, double amount) {
		System.getInstance().navigatePayment().pay(cardnumber, amount);
		return false;
	}

	public List<Product> usecase2_8_5_Update_inventory(int guestID) {
		return System.getInstance().getGuest(guestID).getCart().getItems();
	}

}
