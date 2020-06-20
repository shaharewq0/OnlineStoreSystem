package Domain.Store;

public class Product_boundle {
    private int id;
    private String label;
    private int amount;
    public Product item;

    public Product_boundle(Product p,int amount){
        item = p;
        this.amount = amount;
        label="hello";
    }

    public Product_boundle(){}

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getLabel() {
        return label;
    }

    public Product getItem() {
        return item;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    @Override
    public String toString(){
        return label;
    }

    public void add(int ToAdd) {
        amount += ToAdd;
    }

    //TODO
    public int remove(int ToRemove) {
        if (size() < ToRemove) {
            int temp = size();
            amount -= temp;
            return temp;
        }
            amount -= ToRemove;
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
