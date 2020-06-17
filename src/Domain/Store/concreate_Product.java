package Domain.Store;

public class concreate_Product {
    private int id;
    private String lable ="this is a product";

    @Override
    public String toString(){
        return lable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getLable() {
        return lable;
    }
}
