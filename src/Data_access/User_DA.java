package Data_access;

import Domain.RedClasses.User;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class User_DA implements DOA<User> {

    private EntityManager entityManager;
    private String TABLE_NAME="USER_TABLE";

    public User_DA(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("persistence");
        entityManager= emf.createEntityManager();
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.ofNullable(entityManager.find(User.class,id));
    }

    @Override
    public List<User> getAll() {
        Query query=entityManager.createQuery("SELECT * FROM "+TABLE_NAME);
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        executeInsideTransaction(entityManager -> entityManager.persist(user));
    }

    @Override
    public void update(User user, String[] params) {
        //TODO: add set options
        executeInsideTransaction(entityManager -> entityManager.remove(user));
    }

    @Override
    public void delete(User user) {
        executeInsideTransaction(entityManager -> entityManager.remove(user));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
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
