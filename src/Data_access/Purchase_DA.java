package Data_access;


import Domain.Store.Purchase;

import java.util.Optional;

public class Purchase_DA extends DOA<Purchase> {

    public Purchase_DA(){
        super();
        TABLE_NAME="USER_PURCHASE_TABLE";
    }

    @Override
    public Optional<Purchase> get(int id) {
        return Optional.ofNullable(entityManager.find(Purchase.class,id));
    }

    @Override
    public void save(Purchase Purchase) {
        executeInsideTransaction(entityManager -> entityManager.persist(Purchase));
    }

    @Override
    public void update(Purchase Purchase, String[] params) {
        //TODO : add set to user purchase
        executeInsideTransaction(entityManager -> entityManager.merge(Purchase));
    }

    @Override
    public void delete(Purchase Purchase) {
        executeInsideTransaction(entityManager -> entityManager.remove(Purchase));
    }
}
