package Data_access;

import Domain.RedClasses.shoppingBasket;

import java.util.Optional;

public class ShoppingBasket_DA extends DOA<shoppingBasket> {

    public ShoppingBasket_DA(){
        super();
        TABLE_NAME="SHOPPING_BASKET_TABLE";
    }

    @Override
    public Optional<shoppingBasket> get(int id) {
        return Optional.ofNullable(entityManager.find(shoppingBasket.class,id));
    }

    @Override
    public void save(shoppingBasket shoppingBasket) {
        executeInsideTransaction(entityManager -> entityManager.persist(shoppingBasket));
    }

    @Override
    public void update(shoppingBasket shoppingBasket, String[] params) {
        //TODO: add set to shopping basket
        executeInsideTransaction(entityManager -> entityManager.merge(shoppingBasket));
    }

    @Override
    public void delete(shoppingBasket shoppingBasket) {
        executeInsideTransaction(entityManager -> entityManager.remove(shoppingBasket));
    }


}
