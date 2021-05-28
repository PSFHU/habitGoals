package hu.mik.prog4.habbitgoals.service.goal;

import hu.mik.prog4.habbitgoals.entity.goal.MainGoal;
import hu.mik.prog4.habbitgoals.repository.goal.MainGoalAbstractRepository;
import hu.mik.prog4.habbitgoals.service.Service;

import java.util.List;

public class MainGoalService implements GoalService<MainGoal> , Service<MainGoal> {

    private final MainGoalAbstractRepository mainGoalRepository;
    private final SideGoalService sideGoalService;

    public MainGoalService() {
        mainGoalRepository = new MainGoalAbstractRepository();
        sideGoalService = new SideGoalService();
    }

    @Override
    public List<MainGoal> listAll() {
        return mainGoalRepository.listAll();
    }

    @Override
    public MainGoal findById(Long id) {
        return mainGoalRepository.findById(id);
    }

    @Override
    public MainGoal create(MainGoal goal) {
        return mainGoalRepository.create(goal);
    }

    @Override
    public MainGoal update(MainGoal goal) {
        return mainGoalRepository.update(goal);
    }

    @Override
    public boolean deleteById(Long id) {
        return mainGoalRepository.deleteById(id);
    }

    @Override
    public boolean isCompleted(Long id) {
        return sideGoalService.listAll().stream().filter(sideGoal -> sideGoal.getMainGoalId().equals(id))
                .allMatch(sideGoal -> sideGoalService.isCompleted(sideGoal.getId()))
                ;
    }
}
