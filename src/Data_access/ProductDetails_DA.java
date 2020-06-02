package Data_access;

import Domain.info.ProductDetails;

import java.util.Objects;
import java.util.Optional;

public class ProductDetails_DA extends DOA<ProductDetails> {

    public ProductDetails_DA(){
        super();
        TABLE_NAME="PRODUCT_DETAILS_TABLE";
    }

    @Override
    public Optional<ProductDetails> get(int id) {
        return Optional.ofNullable(entityManager.find(ProductDetails.class,id));
    }

    @Override
    public void save(ProductDetails productDetails) {
        executeInsideTransaction(entityManager -> entityManager.persist(productDetails));
    }

    @Override
    public void update(ProductDetails productDetails, String[] params) {
        //TODO: add set to Product details
        productDetails.setAmount(Integer.parseInt(Objects.requireNonNull(params[0],"Amount cant be null")));
        executeInsideTransaction(entityManager -> entityManager.merge(productDetails));
    }

    @Override
    public void delete(ProductDetails productDetails) {
        executeInsideTransaction(entityManager -> entityManager.remove(productDetails));
    }
}
