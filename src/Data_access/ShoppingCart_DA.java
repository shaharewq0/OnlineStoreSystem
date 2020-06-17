package Data_access;

import Domain.UserClasses.shoppingCart;

import java.util.Optional;

public class ShoppingCart_DA extends DOA<shoppingCart>{

    public ShoppingCart_DA(){
        super();
        TABLE_NAME="SHOPPING_CART_TABLE";
    }

    @Override
    public Optional<shoppingCart> get(int id) {
        return Optional.ofNullable(entityManager.find(shoppingCart.class,id));
    }

    @Override
    public void save(shoppingCart shoppingCart) {
        executeInsideTransaction(entityManager -> entityManager.persist(shoppingCart));
    }

    @Override
    public void update(shoppingCart shoppingCart, String[] params) {
        //TODO : add set to shopping cart
        executeInsideTransaction(entityManager -> entityManager.merge(shoppingCart));
    }

    @Override
    public void delete(shoppingCart shoppingCart) {
        executeInsideTransaction(entityManager -> entityManager.remove(shoppingCart));
    }
}
