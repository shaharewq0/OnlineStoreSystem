package tests.AcceptanceTests.auxiliary;

import Domain.info.ProductDetails;

import java.util.Arrays;

public class Products {
    public static ProductDetails PRODUCT1 = new ProductDetails("product 1", Arrays.asList("cat1"), "store", 5, 10.0, 3);
    public static ProductDetails PRODUCT2 = new ProductDetails("product 2", Arrays.asList("cat2"), "store", 5,20.0,  4);
    public static ProductDetails PRODUCT3 = new ProductDetails("product 3", Arrays.asList("cat1"), "store", 5,30.0,  2);
    public static ProductDetails PRODUCT1_CHANGED_PRICE = new ProductDetails("product 1", Arrays.asList("cat1"), "store", 5,50.0,  3);
    public static ProductDetails PRODUCT2_CHANGED_CATEGORY = new ProductDetails("product 2", Arrays.asList("cat1"), "store", 5,20.0,  4);
    public static ProductDetails PRODUCT3_CHANGED_AMOUNT = new ProductDetails("product 3", Arrays.asList("cat1"), "store", 3,30.0,  2);
    public static String PRODUCT_THAT_DONT_EXIST = "wrong product";
}
