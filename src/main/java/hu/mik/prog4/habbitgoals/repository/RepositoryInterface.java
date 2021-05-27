package hu.mik.prog4.habbitgoals.repository;

import java.util.List;

public interface RepositoryInterface<T> {
    List<T> listAll();

    T findById(Long id);

    T add(T goal);

    T edit(T goal);

    boolean deleteById(Long id);
}
