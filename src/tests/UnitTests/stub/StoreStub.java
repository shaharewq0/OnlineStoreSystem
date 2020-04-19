package tests.UnitTests.stub;

import Store.IStore;
import Store.IUser;
import Store.Product;

import java.util.LinkedList;
import java.util.List;

public class StoreStub implements IStore {
    UserStub Owner;
    UserStub manager;

    public void setOwner(UserStub owner) {
        Owner = owner;
    }

    public void setManager(UserStub manager) {
        this.manager = manager;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public boolean addProduct(Product p) {
        return false;
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public List<Product> findProductByKeyword(String keyword) {
        return null;
    }

    @Override
    public int getRating() {
        return 0;
    }

    @Override
    public boolean removeProduct(Product p) {
        return false;
    }

    @Override
    public boolean editProduct(Product OLD_p, Product NEW_p) {
        return false;
    }

    @Override
    public boolean appointOwner(IUser user) {
        return false;
    }

    @Override
    public boolean appointManager(IUser user) {
        return false;
    }

    @Override
    public boolean fireManager(IUser user) {
        return false;
    }

    @Override
    public List<String> viewPurchaseHistory() {
        LinkedList<String> A=new LinkedList<>();
        A.add("AN ITEM");
        return A;
    }
}
