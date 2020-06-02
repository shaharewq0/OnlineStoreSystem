package Data_access;

import Domain.RedClasses.User;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class User_DA extends DOA<User> {


    public User_DA(){
        super();
        TABLE_NAME="USER_TABLE";
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

}
