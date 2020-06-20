package Domain.Store;

public class Product_boundle {
    final String lable ="this is a product";
    private int amount = 0;
    //TODO add item
    public Product item;

    public Product_boundle(Product p,int amount){
        item = p;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return lable;
    }

    public void add(int ToAdd) {
        amount += ToAdd;
    }

    //TODO
    public int remove(int ToRemove) {
        if (size() < ToRemove) {
            int temp = size();
            remove(temp);
            return temp;
        }
            remove(ToRemove);
            return ToRemove;

    }

    public int size() {
        return amount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Product_boundle))
            return false;

        Product_boundle p = (Product_boundle) other;
        if (item.equals(p.item) & size() == ((Product_boundle) other).size())
            return true;
        return false;

    }
}
