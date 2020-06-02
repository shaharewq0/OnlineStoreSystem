package Data_access;

import Domain.info.StoreInfo;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class StoreInfo_DA extends DOA<StoreInfo> {

    public StoreInfo_DA(){
        super();
        TABLE_NAME="STORE_INFO_TABLE";
    }

    @Override
    public Optional<StoreInfo> get(int id) {
        return Optional.ofNullable(entityManager.find(StoreInfo.class,id));
    }

    @Override
    public void save(StoreInfo storeInfo) {
        executeInsideTransaction(entityManager -> entityManager.persist(storeInfo));
    }

    @Override
    public void update(StoreInfo storeInfo, String[] params) {
        //TODO: ADD SET actions
        executeInsideTransaction(entityManager -> entityManager.merge(storeInfo));
    }

    @Override
    public void delete(StoreInfo storeInfo) {
        executeInsideTransaction(entityManager -> entityManager.remove(storeInfo));
    }
}
