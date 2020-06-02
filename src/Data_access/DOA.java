package Data_access;

import java.util.List;
import java.util.Optional;

//DATA ACCESS OBJECT
public interface DOA<T> {
    Optional<T> get(int id);
    List<T> getAll();
    void save(T t);
    void update(T t,String[] params);
    void delete(T t);
}
