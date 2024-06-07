package Application.repository;

import Application.model.UsersChat;

import java.util.List;

public interface ServiceInterface<T> {
    String saveService(T t);

    String updateService(T t);

    String deleteService(T t);

    T findId(long id);

    List<T> findAll();
}
