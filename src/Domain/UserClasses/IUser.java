package Domain.UserClasses;

import java.util.List;

import Domain.Store.workers.Store_role;

public interface IUser {
//   // public boolean register(String id, String password);
//    public boolean login(String id, String password);
//   // public StoreImp watchStoreDetails(String name);
//    public List<StoreImp> watchAllStores();
//    public Collection<Product> watchProductsInStore(String name);
//    //public List<Product> searchProductsByName(String name);
//   // public List<Product> searchProductsByCategory(String category);
//    //public List<Product> searchProductsByKeyword(String keyword);
//    //public List<Product> filterByPrice(List<Product> base , int min , int max);
//    //public List<Product> filterByRating(List<Product> base , int min , int max);
//    //public List<Product> filterByCategory(List<Product> base , String category);
//    //public List<Product> filterByStoreRating(List<Product> base , int min, int max);
//    public boolean saveProductInBasket(String productName , String storeName);
//    //ublic List<ProductDetails> getProductsInCart();
//    public int deleteProductInBasket(String productName , String storeName,int num);
//   // public boolean purchase();
//    public boolean logout();
//    public boolean openStore(String name, String address, int rating);
//    public List<shoppingCart> watchHistory();
//    public boolean isOwner();
//    public boolean isManager();
//    public boolean isRegistered();
//    public shoppingCart getCart();
     List<UserPurchase> getPurchaseHistory();

	 boolean getFired(String store);

	 boolean appointAsOwner(Store_role newRole);

	 boolean appointAsManager(Store_role newRole);

	 String getName();

	 int getId();

	 void setId(int id);

//	public boolean appointAsOwner(Store_role creator, String store);
}
