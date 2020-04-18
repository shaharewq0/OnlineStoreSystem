package Store.StoreOwner.OwnerTest;

import store_System.User;

public class UserStub implements User {
    boolean manager=false;
    boolean owner=false;

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    @Override
    public boolean isManager() {
        return manager;
    }

    @Override
    public boolean isOwner() {
        return owner;
    }
}
