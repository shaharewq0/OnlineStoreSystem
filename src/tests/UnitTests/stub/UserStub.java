package tests.UnitTests.stub;

import java.util.List;

import Domain.UserClasses.IUser;
import Domain.UserClasses.UserPurchase;
import Domain.Store.workers.Store_role;

public class UserStub implements IUser {
    boolean manager=false;
    boolean owner=false;

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
//
//    @Override
//    public boolean isManager() {
//        return manager;
//    }
//
//    @Override
//    public boolean isOwner() {
//        return owner;
//    }
//
//    @Override
//    public boolean isRegistered() {
//        return true;
//    }
//
//    @Override
//    public boolean register(String id, String password) {
//        return false;
//    }
//
//    @Override
//    public boolean login(String id, String password) {
//        return false;
//    }
//
//    @Override
//    public StoreImp watchStoreDetails(String name) {
//        return null;
//    }
//
//    @Override
//    public List<StoreImp> watchAllStores() {
//        return null;
//    }
//
//    @Override
//    public List<Product> watchProductsInStore(String name) {
//        return null;
//    }
//
//    @Override
//    public List<Product> searchProductsByName(String name) {
//        return null;
//    }
//
//    @Override
//    public List<Product> searchProductsByCategory(String category) {
//        return null;
//    }
//
//    @Override
//    public List<Product> searchProductsByKeyword(String keyword) {
//        return null;
//    }
//
//    @Override
//    public List<Product> filterByPrice(List<Product> base, int min, int max) {
//        return null;
//    }
//
//    @Override
//    public List<Product> filterByRating(List<Product> base, int min, int max) {
//        return null;
//    }
//
//    @Override
//    public List<Product> filterByCategory(List<Product> base, String category) {
//        return null;
//    }
//
//    @Override
//    public List<Product> filterByStoreRating(List<Product> base, int min, int max) {
//        return null;
//    }
//
//    @Override
//    public boolean saveProductInBasket(String productName, String storeName) {
//        return false;
//    }
////
////    @Override
////    public List<MyPair<Product, Integer>> getProductsInCart() {
////        return null;
////    }
////
//    @Override
//    public int deleteProductInBasket(String productName, String storeName, int num) {
//        return 0;
//    }
//
////    @Override
////    public boolean purchase() {
////        return false;
////    }
//
//    @Override
//    public boolean logout() {
//        return false;
//    }
//
//    @Override
//    public boolean openStore(String name, String address, int rating) {
//        return false;
//    }
//
//    @Override
//    public List<shoppingCart> watchHistory() {
//        return null;
//    }
//
//	@Override
//	public shoppingCart getCart() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<UserPurchase> getPurchaseHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFired(String store) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appointAsOwner(Store_role newRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appointAsManager(Store_role newRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public void setId(int id) {

	}


}
