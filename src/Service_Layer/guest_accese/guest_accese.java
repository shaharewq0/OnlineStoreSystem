package Service_Layer.guest_accese;

import java.util.LinkedList;
import java.util.List;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.info.StoreInfo;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.ProductDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class guest_accese {

	public boolean usecase2_3_login(String username, String password) {
		return System.getInstance().login(username, password) != null;
	}

	public boolean usecase2_2_guest_register(String username, String password) {
		return System.getInstance().register(username, password);
	}

	public StoreDetails usecase2_4A_getStoreDetails(String storeName) {
		StoreImp store = System.getInstance().getStoreDetails(storeName);
		if (store == null)
			return null;
		return new StoreDetails(store);
	}

	public StoreInfo usecase2_4B_getStoreProdacts(String storeName) {
		return System.getInstance().getStoreDetails(storeName).getMyInfo();
	}

	public List<ProductDetails> usecase2_5A_searchProductByName(String name) {
		return ProductDetails.adapteProdactList(System.getInstance().searchProductsByName(name));
	}

	public List<ProductDetails> usecase2_5B_searchProductByCategory(String category) {
		return ProductDetails.adapteProdactList(System.getInstance().searchProductsByCategory(category));
	}

	public List<ProductDetails> usecase2_5C_searchProductByKeyword(String keyword) {
		return ProductDetails.adapteProdactList(System.getInstance().searchProductsByKeyword(keyword));
	}

	public List<ProductDetails> usecase2_5D_1_FilterbyPrice(int minPrice, int maxPrice) {

		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getPrice() < maxPrice && product.getPrice() > minPrice)
					output.add(new ProductDetails(product, product.getAmount()));
			}
		}

		return output;

	}

	public List<ProductDetails> usecase2_5D_2_FilterbyRating(int minRating, int maxRating) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getRating() < maxRating && product.getPrice() > minRating)
					output.add(new ProductDetails(product, product.getAmount()));
			}
		}
		return output;
	}

	public List<ProductDetails> usecase2_5D_3_FilterbyCategory(List<String> prodacts, String category) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getCategory().contains(category))
					output.add(new ProductDetails(product, product.getAmount()));
			}
		}
		return output;

	}

	public List<ProductDetails> usecase2_5D_4_FilterbyStoreRating(int minRating, int maxRating) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			if (store.getRating() > minRating && store.getRating() < maxRating)
				for (Product product : store.getProducts()) {
					output.add(new ProductDetails(product, product.getAmount()));
				}
		}
		return output;
	}

	public boolean usecase2_6_saveProductToBasket(int guestID, String storename, String prodactname, int amount) {

		return System.getInstance().getGuest(guestID).saveProductInBasket(prodactname, storename);
	}

	public List<ProductDetails> usecase2_7A_WatchProdactsInCart(int guestID) {
		return System.getInstance().getGuest(guestID).getProductsInCart();
	}

	public int usecase2_7b_RemoveProdactsInCart(int guestID, String storename, String prodactname, int amount) {
		// TODO imp
		return System.getInstance().getGuest(guestID).deleteProductInBasket(prodactname, storename, amount);
	}

	public boolean usecase2_8_Purchase_products() {
		return false;
	}

	public List<ProductDetails> usecase2_8_1_Check_available_products(int guestID) {
		return System.getInstance().CheckItemAvailable(System.getInstance().getGuest(guestID).getProductsInCart());
	}

	public double usecase2_8_2_Calculate_price(int guestID) {
		return System.getInstance().getGuest(guestID).getCart().CalcPrice();
	}

	public boolean usecase2_8_3_Purchase_products() {
		return false;
	}

	public boolean usecase2_8_4Purchase_products() {
		return false;
	}

	public boolean usecase2_8_5_Purchase_products() {
		return false;
	}

}
