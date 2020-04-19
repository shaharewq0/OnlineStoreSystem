package tests.UnitTests.stub;

import Store.Store;
import Store.Item;
import store_System.User;

import java.util.LinkedList;
import java.util.List;

public class StoreStub implements Store {
    UserStub Owner;
    UserStub manager;

    public void setOwner(UserStub owner) {
        Owner = owner;
    }

    public void setManager(UserStub manager) {
        this.manager = manager;
    }

    @Override
    public boolean addItem(Item item) {
        return true;
    }

    @Override
    public boolean removeItem(Item item) {
        return true;
    }

    @Override
    public boolean editItem(Item OLD_item, Item NEW_item) {
        return true;
    }

    @Override
    public void appointOwner(User user) {

    }

    @Override
    public void appointManager(User user) {

    }

    @Override
    public void fireManager(User user) {

    }

    @Override
    public List<String> viewPurchaseHistory() {
        LinkedList<String> A=new LinkedList<>();
        A.add("AN ITEM");
        return A;
    }
}
