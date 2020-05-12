package Domain.info;



import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Domain.Store.Product;

public class ProductDetails {
    public String getName() {
        return name;
    }

    private List<String> keyWords;
    private String name;
    private List<String> category;
    private String storeName;
    private int amount;
    private double price;

    public ProductDetails(String name, List<String> category, String storeName, int amount,double price) {
        this.name = name;
        this.category = new LinkedList<>();
        this.category.addAll(category);
        this.storeName = storeName;
        this.amount = amount;
        this.price = price;
    }
    
    public ProductDetails(Product pro, int amount) {
        this.name = pro.getName();
        this.category = new LinkedList<String>();
        category.addAll(pro.getCategory());
        this.storeName = pro.getStore().getName();
        this.amount = amount;
        keyWords.addAll(pro.getKeyWords());
        this.price = pro.getPrice();
    }



	public double getPrice() {
    	return price;
    }
    
    public List<String>  getKeyWords(){
    	return keyWords;
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
		return new ProductDetails(other.name,other.getCategory(),other.getStoreName(),other.getAmount(),other.price);
	}
}
