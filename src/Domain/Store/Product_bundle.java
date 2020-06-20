package Domain.Store;

public class Product_bundle {
    private String label;
    private int amount;


    public Product_bundle(){
    }

    public String getLabel() {
        return label;
    }

    public int getAmount() {
        return amount;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return label;
    }

    public void add(int ToAdd) {
        amount += ToAdd;
    }

    public void remove(int ToRemove){
        amount -=ToRemove;
    }

    public int size() {
        return amount;
    }
}
