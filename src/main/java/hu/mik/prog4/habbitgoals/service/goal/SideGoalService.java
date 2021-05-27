package hu.mik.prog4.habbitgoals.service.goal;

import hu.mik.prog4.habbitgoals.entity.goal.SideGoal;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import hu.mik.prog4.habbitgoals.repository.goal.SideGoalRepository;
import hu.mik.prog4.habbitgoals.repository.measure.MeasureValueRepository;
import hu.mik.prog4.habbitgoals.service.measure.MeasureValueService;

import java.util.List;
import java.util.Optional;

public class SideGoalService implements GoalService<SideGoal>{

    private final SideGoalRepository sideGoalRepository;
    private final MeasureValueService measureValueService;

    public SideGoalService() {
        this.sideGoalRepository = new SideGoalRepository();
        measureValueService = new MeasureValueService();
    }

    @Override
    public List<SideGoal> listAll() {
        return sideGoalRepository.listAll();
    }

    public List<SideGoal> listAllMainGoalId(Long id){
        return sideGoalRepository.listAllMainGoalId(id);
    }

    @Override
    public SideGoal findById(Long id) {
        return sideGoalRepository.findById(id);
    }

    @Override
    public SideGoal add(SideGoal goal) {
        return sideGoalRepository.add(goal);
    }

    @Override
    public SideGoal edit(SideGoal goal) {
        return sideGoalRepository.edit(goal);
    }

    @Override
    public boolean deleteById(Long id) {
        return sideGoalRepository.deleteById(id);
    }

    @Override
    public boolean isCompleted(Long id) {
        SideGoal byId = sideGoalRepository.findById(id);
        return measureValueService.listAllOnMeasureFieldId(byId.getMeasureFieldId())
                .stream()
                .map(MeasureValue::getValue)
                .max(Double::compareTo)
                .filter(aDouble -> aDouble <= byId.getGoalValue())
                .isPresent();
    }
}
