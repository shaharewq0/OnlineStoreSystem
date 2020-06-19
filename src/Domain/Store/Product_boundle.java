package Domain.Store;

public class Product_boundle {
    final String lable ="this is a product";
    private int amount = 0;
    @Override
    public String toString(){
        return lable;
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
