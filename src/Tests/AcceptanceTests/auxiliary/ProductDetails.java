package Tests.AcceptanceTests.auxiliary;

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

    public String getCategory() {
        return category;
    }

    public String getStoreName() {
        return storeName;
    }
}
