package Store;

import java.util.List;

public interface IProduct {
    public String getName();
    public String getCategory();
    public List<String> getKeyWords();
    public int getPrice();
    public int getRating();
    public Store getStore();
}
