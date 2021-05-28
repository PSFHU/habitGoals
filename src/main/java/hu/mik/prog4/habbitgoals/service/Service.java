package hu.mik.prog4.habbitgoals.service;

import java.util.List;

public interface Service<T> {
    List<T> listAll();

    T findById(Long id);

    T create(T measure);

    T update(T measure);

    boolean deleteById(Long id);
}
