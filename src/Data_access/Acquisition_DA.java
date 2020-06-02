package Data_access;

import Domain.Store.Acquisition;

import java.util.Optional;

public class Acquisition_DA extends DOA<Acquisition> {

    public Acquisition_DA(){
        super();
        TABLE_NAME="ACQUISITION_TABLE";
    }

    @Override
    public Optional<Acquisition> get(int id) {
        return Optional.ofNullable(entityManager.find(Acquisition.class,id));
    }

    @Override
    public void save(Acquisition acquisition) {
        executeInsideTransaction(entityManager -> entityManager.persist(acquisition));
    }

    @Override
    public void update(Acquisition acquisition, String[] params) {
        //TODO : add set tp Acquisition
        executeInsideTransaction(entityManager -> entityManager.merge(acquisition));
    }

    @Override
    public void delete(Acquisition acquisition) {
        executeInsideTransaction(entityManager -> entityManager.remove(acquisition));
    }
}
