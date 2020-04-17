package Store;

import java.util.List;

public class Product {
    private String name;
    private String category;
    private List<String> keyWords;

    public Product(String name , String category, List<String> keyWords) {
        this.name = name;
        this.category=category;
        this.keyWords=keyWords;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }
}
