package Data_access;

import Domain.Store.Discount;

import java.util.Optional;

public class Discount_DA extends DOA<Discount> {

    public Discount_DA(){
        super();
        TABLE_NAME="DISCOUNT_TABLE";
    }

    @Override
    public Optional<Discount> get(int id) {
        return Optional.ofNullable(entityManager.find(Discount.class,id));
    }

    @Override
    public void save(Discount discount) {
        executeInsideTransaction(entityManager -> entityManager.persist(discount));
    }

    @Override
    public void update(Discount discount, String[] params) {
        //TODO : add set to Discount
        executeInsideTransaction(entityManager -> entityManager.merge(discount));
    }

    @Override
    public void delete(Discount discount) {
        executeInsideTransaction(entityManager -> entityManager.remove(discount));
    }
}
