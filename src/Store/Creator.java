package Store;

public class Creator implements Store_role {
    private StoreImp store;

    public Creator(StoreImp store) {
        this.store = store;
    }

    public StoreImp getStore() {
        return store;
    }
}
