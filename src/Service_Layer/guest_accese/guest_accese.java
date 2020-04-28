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

//	public ProductDetails getProductDetails(String storeName, String productName) {
//		Product pro = System.getInstance().getStoreDetails(storeName).findProductByName(productName);
//		if (pro == null)
//			return null;
//		return new ProductDetails(pro);
//	}

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
					output.add(new ProductDetails(product));
			}
		}

		return output;

	}

	public List<ProductDetails> usecase2_5D_2_FilterbyRating(int minRating, int maxRating) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getRating() < maxRating && product.getPrice() > minRating)
					output.add(new ProductDetails(product));
			}
		}
		return output;
	}

	public List<ProductDetails> usecase2_5D_3_FilterbyCategory(List<String> prodacts, String category) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			for (Product product : store.getProducts()) {
				if (product.getCategory().contains(category))
					output.add(new ProductDetails(product));
			}
		}
		return output;

	}

	public List<ProductDetails> usecase2_5D_4_FilterbyStoreRating(int minRating, int maxRating) {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (StoreImp store : System.getInstance().getAllStores()) {
			if (store.getRating() > minRating && store.getRating() < maxRating)
				for (Product product : store.getProducts()) {
					output.add(new ProductDetails(product));
				}
		}
		return output;
	}

	// 2.6 Guest Save product in shopping basket
	public boolean usecase2_6_saveProductToBasket(int guestID, String storename, String prodactname, int amount) {
		return System.getInstance().getGuest(guestID).saveProductInBasket(prodactname, storename);
	}

//	public void addToBasket(String storeName, String productName) {
//		// TODO dont know which user to check
//	}

	public List<ProductDetails> usecase2_7A_WatchProdactsInCart() {
		// TODO imp
		// return System.getInstance().getGuest(guestID);
		return null;
	}

	public List<ProductDetails> usecase2_7b_RemoveProdactsInCart(String prodactname, int amount) {
		// TODO imp
		return null;
	}

	// use case 2
	public List<ProductDetails> getShoppingCart() {
		// TODO dont know which user to check
		return new LinkedList<>();
	}

	public boolean inBasket(String storeName, String productName) {
		// TODO dont know which user to check
		return false;
	}

	// use case 2
	public void clearShoppingCart() {
		// TODO dont know which user to check
	}

	// use case 2
	public boolean hasItem(String storeName, String productName) {
		return System.getInstance().getStoreDetails(storeName).findProductByName(productName) != null;
	}

}
