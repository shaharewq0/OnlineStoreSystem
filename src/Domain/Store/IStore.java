package Domain.Store;

import java.util.Collection;
import java.util.List;

import Domain.Store.workers.StoreManager_Imp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.info.ProductDetails;

public interface IStore {
	public String getName();

	public Collection<Product> getProducts();

	public String getAddress();

	public boolean addProduct(Product p);

	public Product findProductByName(String name);

	public List<Product> findProductByCategory(String category);

	public List<Product> findProductByKeyword(String keyword);

	public int getRating();

	public List<StorePurchase> viewPurchaseHistory() ;

	public Boolean CheckItemAvailable(ProductDetails items);

	public double getPrice(List<ProductDetails> item);

	public Product TakeItem(String name, int amout);

	public String getDiscounts(String name);

	boolean removeProduct(String pName);

	boolean editProduct(String OLD_p, Product NEW_p);

	boolean appointManager(StoreManager_Imp user);

	boolean appointOwner(StoreOwner_Imp user);



	boolean fireWorker(String user);

}
