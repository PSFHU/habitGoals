package hu.mik.prog4.habbitgoals.service.goal;

import hu.mik.prog4.habbitgoals.entity.goal.MainGoal;
import hu.mik.prog4.habbitgoals.entity.goal.SideGoal;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import hu.mik.prog4.habbitgoals.repository.goal.SideGoalAbstractRepository;
import hu.mik.prog4.habbitgoals.service.Service;
import hu.mik.prog4.habbitgoals.service.measure.MeasureValueService;

import java.util.List;
import java.util.stream.Collectors;

public class SideGoalService implements GoalService<SideGoal>, Service<SideGoal> {

    private final SideGoalAbstractRepository sideGoalRepository;
    private final MeasureValueService measureValueService;

    public SideGoalService() {
        this.sideGoalRepository = new SideGoalAbstractRepository();
        measureValueService = new MeasureValueService();
    }

    @Override
    public List<SideGoal> listAll() {
        return sideGoalRepository.listAll();
    }

    @Override
    public SideGoal findById(Long id) {
        return sideGoalRepository.findById(id);
    }

    @Override
    public SideGoal create(SideGoal goal) {
        return sideGoalRepository.create(goal);
    }

    @Override
    public SideGoal update(SideGoal goal) {
        return sideGoalRepository.update(goal);
    }

    @Override
    public boolean deleteById(Long id) {
        return sideGoalRepository.deleteById(id);
    }

    @Override
    public boolean isCompleted(Long id) {
        SideGoal byId = sideGoalRepository.findById(id);
        return measureValueService.listAll()
                .stream()
                .filter(measureValue -> measureValue.getMeasureFieldId().equals(byId.getMeasureFieldId()))
                .collect(Collectors.toList()).stream()
                .map(MeasureValue::getValue)
                .max(Double::compareTo)
                .filter(aDouble -> aDouble >= byId.getGoalValue())
                .isPresent();
    }
}
