package hu.mik.prog4.habbitgoals.service.goal;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;

import java.util.List;

public interface GoalService<T extends Goal> {
    List<T> listAll();

    T findById(Long id);

    T add(T goal);

    T edit(T goal);

    boolean deleteById(Long id);

    boolean isCompleted(Long id);
}
