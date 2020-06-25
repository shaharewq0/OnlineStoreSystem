package Domain.Store;

import Domain.Store.workers.appoints.Appoint_Owner;
import Domain.Store.workers.appoints.Appoint_manager;
import Domain.info.ProductDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IStore {
	public String getName();

	public Collection<Product> getProducts();

	public String getAddress();

	public boolean addProduct(Product p,int amout);

	public Product findProductByName(String name);

	public List<Product> findProductByCategory(String category);

	public List<Product> findProductByKeyword(String keyword);

	public int getRating();

	public List<StorePurchase> viewPurchaseHistory() ;

	public Boolean CheckItemAvailable(ProductDetails items);

    public double getPrice(List<Product_boundle> item);

	public MyPair<Product_boundle,String> TakeItem(String name, int amout);

	public String getDiscounts(String name);

	boolean removeProduct(String pName);

	boolean editProduct(String OLD_p, Product NEW_p);

	boolean appointManager(Appoint_manager user);

	boolean appointOwner(Appoint_Owner user);

	boolean fireWorker(String user);

	public int getId();

	public void setId(int id);
}
