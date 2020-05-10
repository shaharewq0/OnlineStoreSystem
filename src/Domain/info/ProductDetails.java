package Domain.info;



import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Domain.Store.Product;

public class ProductDetails {
    public String getName() {
        return name;
    }

    private String name;
    private List<String> category;
    private String storeName;
    private int amount;

    public ProductDetails(String name, List<String> category, String storeName, int amount) {
        this.name = name;
        this.category = new LinkedList<>();
        this.category.addAll(category);
        this.storeName = storeName;
        this.amount = amount;
    }
    
    public ProductDetails(Product pro, int amount) {
        this.name = pro.getName();
        this.category = new LinkedList<String>();
        category.addAll(pro.getCategory());
        this.storeName = pro.getStore().getName();
        this.amount = amount;
    }

    public List<String>  getCategory() {
        return category;
    }

    public String getStoreName() {
        return storeName;
    }
   
    public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
  
	static public List<ProductDetails> adapteProdactList(Collection<Product> list)
    {
    	LinkedList<ProductDetails> output = new LinkedList<ProductDetails>();
    	for (Product product : list) {
			output.add(new ProductDetails(product,product.getAmount()));
		}
    	return output;
    }

	static public ProductDetails Copy(ProductDetails other)
	{
		return new ProductDetails(other.name,other.getCategory(),other.getStoreName(),other.getAmount());
	}
}
