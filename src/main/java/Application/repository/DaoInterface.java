package Application.repository;

import java.util.List;

public interface DaoInterface<T>{
    String save(T t);

    String delete(T t);

    String update(T t);

    T findId(long id);

    List<T> findAll();
}
