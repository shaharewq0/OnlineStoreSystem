package Service_Layer.guest_accese;

import java.util.LinkedList;
import java.util.List;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.ProductDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class guest_accese {

	public boolean usecase2_3_login(String username, String password) {
		// TODO imp
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

	public ProductDetails usecase2_4B_getStoreProdacts(String storeName) {
		// TODO add imp its currently the one below but that one is wronge
		return null;
	}

	public ProductDetails getProductDetails(String storeName, String productName) {
		Product pro = System.getInstance().getStoreDetails(storeName).findProductByName(productName);
		if (pro == null)
			return null;
		return new ProductDetails(pro);
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

	public List<ProductDetails> usecase2_5D_1_FilterbyPrice(List<String> prodacts, int minPrice, int maxPrice) {
		// TODO imp
		return null;

	}

	public List<ProductDetails> usecase2_5D_2_FilterbyRating(List<String> prodacts, int minRating, int maxRating) {
		// TODO imp
		return null;

	}

	public List<ProductDetails> usecase2_5D_3_FilterbyCategory(List<String> prodacts, String category) {
		// TODO imp
		return null;

	}

	public List<ProductDetails> usecase2_5D_4_FilterbyStoreRating(List<String> prodacts, int minRating, int maxRating) {
		// TODO imp
		return null;
	}

	// 2.6 Guest Save product in shopping basket
	public boolean usecase2_6_saveProductToBasket(String storename, String prodactname, int amount) {
		// TODO imp
		return false;
	}

//	public void addToBasket(String storeName, String productName) {
//		// TODO dont know which user to check
//	}
	
	public List<ProductDetails> usecase2_7A_WatchProdactsInCart() {
		// TODO imp
		return null;
	}
	
	public List<ProductDetails> usecase2_7b_RemoveProdactsInCart(String prodactname,int amount) {
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
