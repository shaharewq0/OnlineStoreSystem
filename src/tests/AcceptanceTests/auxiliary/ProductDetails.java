package tests.AcceptanceTests.auxiliary;



import java.util.LinkedList;
import java.util.List;

import Domain.Store.Product;

public class ProductDetails {
    public String getName() {
        return name;
    }

    private String name;
    private String category;
    private String storeName;

    public ProductDetails(String name, String category, String storeName) {
        this.name = name;
        this.category = category;
        this.storeName = storeName;
    }
    
    public ProductDetails(Product pro) {
        this.name = pro.getName();
        this.category = pro.getCategory();
        this.storeName = pro.getStore().getName();;
    }

    public String getCategory() {
        return category;
    }

    public String getStoreName() {
        return storeName;
    }

    static public List<ProductDetails> adapteProdactList(List<Product> list)
    {
    	LinkedList<ProductDetails> output = new LinkedList<ProductDetails>();
    	for (Product product : list) {
			output.add(new ProductDetails(product));
		}
    	return output;
    }
}
