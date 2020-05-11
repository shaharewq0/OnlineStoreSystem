package tests.AcceptanceTests.auxiliary;


import Domain.Store.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ProductDetails {
    public static ProductDetails PRODUCT1 = new ProductDetails("product 1", "cat1", "store", 10.0, 3, 5);
    public static ProductDetails PRODUCT2 = new ProductDetails("product 2", "cat2", "store", 20.0, 4, 5);
    public static ProductDetails PRODUCT3 = new ProductDetails("product 3", "cat1", "store", 30.0, 2, 5);
    public static ProductDetails PRODUCT1_CHANGED_PRICE = new ProductDetails("product 1", "cat1", "store", 50.0, 3, 5);
    public static ProductDetails PRODUCT2_CHANGED_CATEGORY = new ProductDetails("product 2", "cat1", "store", 20.0, 4, 5);
    public static ProductDetails PRODUCT3_CHANGED_AMOUNT = new ProductDetails("product 3", "cat1", "store", 30.0, 2, 2);
    public static String PRODUCT_THAT_DONT_EXIST = "wrong product";

    private String name;
    private String category;
    private String storeName;
    private double price;
    private int rating;
    private int amount;

    // TODO: REMOVE
    public ProductDetails(String name, String category, String storeName) {
        this.name = name;
        this.category = category;
        this.storeName = storeName;
    }

    public ProductDetails(String name, String category, String storeName, double price, int rating, int amount) {
        this.name = name;
        this.category = category;
        this.storeName = storeName;
        this.price = price;
        this.rating = rating;
        this.amount = amount;
    }

    public ProductDetails(Product pro) {    //TODO
        this.name = pro.getName();
        this.category = pro.getCategory();
        this.storeName = pro.getStore().getName();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getStoreName() {
        return storeName;
    }

    public double getPrice() {
        return price;
    }

    static public List<ProductDetails> adapteProdactList(List<Product> list)
    {
    	LinkedList<ProductDetails> output = new LinkedList<>();
    	for (Product product : list) {
			output.add(new ProductDetails(product));
		}
    	return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetails that = (ProductDetails) o;
        return Double.compare(that.price, price) == 0 &&
                rating == that.rating &&
                amount == that.amount &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category) &&
                Objects.equals(storeName, that.storeName);
    }
}
