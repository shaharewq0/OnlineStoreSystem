package Store;

public class Creator implements Store_role {
    private Store store;

    public Creator(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }
}
