package Data_access;

import Domain.Store.Product;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class Product_DA extends DOA<Product>{

    public Product_DA(){
        super();
        TABLE_NAME="PRODUCT_TABLE";
    }

    @Override
    public Optional<Product> get(int id) {
        return Optional.ofNullable(entityManager.find(Product.class,id));
    }

    @Override
    public List<Product> getAll() {
        Query query = entityManager.createQuery("SELECT * FROM"+TABLE_NAME);
        return query.getResultList();
    }

    @Override
    public void save(Product product) {
        executeInsideTransaction(entityManager -> entityManager.persist(product));
    }

    @Override
    public void update(Product product, String[] params) {
        //TODO: SET ON PRODUCT
        executeInsideTransaction(entityManager -> entityManager.merge(product));
    }

    @Override
    public void delete(Product product) {
        executeInsideTransaction(entityManager -> entityManager.remove(product));
    }
}
