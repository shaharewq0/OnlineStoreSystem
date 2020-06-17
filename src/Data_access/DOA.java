package Data_access;

import Domain.Store.Product;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

//DATA ACCESS OBJECT
public abstract class DOA<T> {

    protected EntityManager entityManager;
    protected String TABLE_NAME;


    public DOA(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("persistence");
        entityManager=emf.createEntityManager();
    }


    public List<T> getAll(){
        Query query = entityManager.createQuery("SELECT * FROM"+TABLE_NAME);
        return query.getResultList();
    }


    public abstract Optional<T> get(int id);
    public abstract void save(T t);
    public abstract void update(T t,String[] params);
    public abstract void delete(T t);

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
