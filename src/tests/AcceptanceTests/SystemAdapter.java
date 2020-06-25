package tests.AcceptanceTests;

import Domain.Store.Product_boundle;
import Domain.UserClasses.UserPurchase;
import Domain.Store.Product;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.info.StoreInfo;
import Domain.store_System.System;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import Service_Layer.owner_accese.owner_accese;
import Service_Layer.userAddress;
import extornal.payment.CreditCard;
import tests.AcceptanceTests.auxiliary.*;

import java.util.LinkedList;
import java.util.List;

public class SystemAdapter {
	public void init(DummyPayment payment, DummySupply supply) {
		// TODO
	}

	public void resetSystem() {
		System.getInstance().resetSystem();
	}

	public int newGuest() {
		return guest_accese.ImNew();
	}

	// 2.2
	public boolean register(String username, String password) {
		return guest_accese.usecase2_2_guest_register(username, password);
	}

	public void removeUser(String username, String password) {
		System.getInstance().removeUser(username, password);
	}

	// 2.3
	public boolean login(String username, String password) {
		return guest_accese.usecase2_3_login(guest_accese.ImNew(), username, password);
	}

	public int login_code(String username, String password) {
		int id = guest_accese.ImNew();
		if(guest_accese.usecase2_3_login(guest_accese.ImNew(), username, password))
			return id;
		return -1;
	}


	// 2.3
	public boolean login(int guestID, String username, String password) {
		return guest_accese.usecase2_3_login(guestID, username, password);
	}

	// 2.4A
	public StoreDetails getStoreDetails(String storeName) {
		return guest_accese.usecase2_4A_getStoreDetails(storeName);
	}

	// 2.4B
	public List<ProductDetails> getProductsFromStore(String storeName) {
		StoreInfo s = guest_accese.usecase2_4B_getStoreProdacts(storeName);
		if(s == null)
			return null;
		return s.products;
	}

	// 2.5A
	public List<ProductDetails> searchProductByName(String name) {
		return guest_accese.usecase2_5A_searchProductByName(name);
	}

	// 2.5B
	public List<ProductDetails> searchProductByCategory(String category) {
		return guest_accese.usecase2_5B_searchProductByCategory(category);
	}

	// 2.5C
	public List<ProductDetails> searchProductByKeyword(String keyword) {
		return guest_accese.usecase2_5C_searchProductByKeyword(keyword);
	}

	// 2.5D1
	public List<ProductDetails> filterByPrice(double minPrice, double maxPrice) {
		return guest_accese.usecase2_5D_1_FilterbyPrice(minPrice, maxPrice);
	}

	// 2.5D2
	public List<ProductDetails> filterByRating(int minRating, int maxRating) {
		return guest_accese.usecase2_5D_2_FilterbyRating(minRating, maxRating);
	}

	// 2.5D3
	public List<ProductDetails> filterByCategory(String category) {
		return guest_accese.usecase2_5D_3_FilterbyCategory(category);
	}

	// 2.5D4
	public List<ProductDetails> filterByStoreRating(int minRating, int maxRating) {
		return guest_accese.usecase2_5D_4_FilterbyStoreRating(minRating, maxRating);
	}

	// 2.6
	public boolean addToBasket(int guestID, String storeName, String productName, int amount) {
		return guest_accese.usecase2_6_saveProductToBasket(guestID, storeName, productName, amount);
	}

	// 2.7A
	public List<ProductDetails> watchShoppingCart(int guestID) {
		return guest_accese.usecase2_7A_WatchProdactsInCart(guestID);
	}

	// 2.7B
	public boolean removeProductsFromCart(int guestID, String storeName, String productName, int amount) {
		return guest_accese.usecase2_7b_RemoveProdactsInCart(guestID, storeName, productName, amount) > 0;
	}

	// 2.8
	public boolean purchase(int guestID, String card, String edate, String css, String cardOwner, String shipAdress) {
		return guest_accese.usecase2_8_Purchase_products(guestID, new CreditCard(card, edate, css, cardOwner, "1234"), new userAddress("United States", "Washington, D.C.", "1600 Pennsylvania Avenue NW", "20500", "Donald Trump"));
	}

	// 3.1
	public boolean logout(String username, String password) {
		return member_accese.usecase3_1_Logout(username, password);
	}

	// 3.2
	public boolean openStore(String username, String password, StoreDetails storeDetails) {
		return member_accese.usecase3_2_OpenStore(username, password, storeDetails);
	}

	// 3.7
	public List<UserPurchase> getPurchaseHistory(String username, String password) {
				return member_accese.usecase3_7_ReviewPurchasesHistory(username, password);
	}

	// 4.1.1
	public boolean addProduct(String username, String password, String storeName, ProductDetails product) {
		return owner_accese.usecase4_1_1_AddingProdacsToStore(username, password, storeName,
				new Product_boundle(new Product(product),product.getAmount()));
	}

	// 4.1.2
	public boolean RemoveProduct(String username, String password, String storeName, String productName) {
		return owner_accese.usecase4_1_2_RemoveItem(username, password, storeName, productName);
	}

	// 4.1.3
	public boolean EditProduct(String username, String password, String storeName, String productName,
							   ProductDetails newProductDet) {
		return owner_accese.usecase4_1_3_EditProduct(username, password, storeName, productName, newProductDet);
	}

	// 4.3
    public boolean appointOwner(String myusername, String myPassword, String storeName, String hisusername,String hisPassword) {
		return owner_accese.usecase4_3_appointOwner(myusername, myPassword, storeName, hisusername, hisPassword);
	}

	// 4.5
    public boolean appointManager(String myusername, String myPassword, String storeName, String hisusername,String hisPassword) {
		return  owner_accese.usecase4_5_appointManager(myusername, myPassword, storeName, hisusername, hisPassword);
    }

    // 4.6
    public boolean editManagerPermissions(String myusername, String myPassword,String storename, String managername,
                                          List<String> permissions) {
		return owner_accese.usecase4_6_editMangagerPermesions(myusername, myPassword, storename, managername, permissions);
    }

    // 4.7
    public boolean fireManager(String myusername, String myPassword, String storeName, String hisusername) {
		return owner_accese.usecase4_7_FireManager(myusername, myPassword, storeName, hisusername);
    }

	// 4.10
	public List<StorePurchase> getStoreSellingHistory(String username, String password, String storeName) {
		return owner_accese.usecase4_10_ViewAcquisitionHistory(username, password, storeName);
	}

	// 4.9
	public List<Question> getStoreQuestions(String storeName) {
		// TODO this is not implemented in version 1
		return new LinkedList<>();
	}

	// 4.9
	public void askQuestion(String storeName, Question question) {
		// TODO this is not implemented in version 1

	}

	// 4.9
	public void answerQuestion(Question question) {
		// TODO this is not implemented in version 1

	}
}
