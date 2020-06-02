package Data_access;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

    public abstract Optional<T> get(int id);
    public abstract List<T> getAll();
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
