package hu.mik.prog4.habbitgoals.service.goal;

import hu.mik.prog4.habbitgoals.entity.goal.MainGoal;
import hu.mik.prog4.habbitgoals.entity.goal.SideGoal;
import hu.mik.prog4.habbitgoals.repository.goal.MainGoalRepository;
import hu.mik.prog4.habbitgoals.repository.goal.SideGoalRepository;

import java.util.List;

public class MainGoalService implements GoalService<MainGoal> {

    private final MainGoalRepository mainGoalRepository;
    private final SideGoalService sideGoalService;

    public MainGoalService() {
        mainGoalRepository = new MainGoalRepository();
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
    public MainGoal add(MainGoal goal) {
        return mainGoalRepository.add(goal);
    }

    @Override
    public MainGoal edit(MainGoal goal) {
        return mainGoalRepository.edit(goal);
    }

    @Override
    public boolean deleteById(Long id) {
        return mainGoalRepository.deleteById(id);
    }

    @Override
    public boolean isCompleted(Long id) {
        return sideGoalService.listAllMainGoalId(id)
                .stream()
                .allMatch(sideGoal -> sideGoalService.isCompleted(sideGoal.getId()))
                ;
    }
}
