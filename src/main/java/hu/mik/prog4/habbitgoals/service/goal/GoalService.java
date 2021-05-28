package hu.mik.prog4.habbitgoals.service.goal;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;

import java.util.List;

public interface GoalService<T extends Goal> {

    boolean isCompleted(Long id);
}
