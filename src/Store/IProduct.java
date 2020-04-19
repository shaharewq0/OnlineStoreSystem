package Store;

import java.util.List;

public interface IProduct {
    public String getName();
    public String getCategory();
    public List<String> getKeyWords();
    public double getPrice();
    public int getRating();
    public StoreImp getStore();
}
