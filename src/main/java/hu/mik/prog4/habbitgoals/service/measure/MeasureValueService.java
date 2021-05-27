package hu.mik.prog4.habbitgoals.service.measure;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import hu.mik.prog4.habbitgoals.repository.measure.MeasureValueRepository;

import java.util.List;

public class MeasureValueService implements MeasureService<MeasureValue> {

    private final MeasureValueRepository measureValueRepository;

    public MeasureValueService() {
        measureValueRepository = new MeasureValueRepository();
    }

    @Override
    public List<MeasureValue> listAll() {
        return measureValueRepository.listAll();
    }

    public List<MeasureValue> listAllOnMeasureFieldId(Long id){
        return measureValueRepository.listAllOnMeasureFieldId(id);
    }

    public double getAvgOnMeasureFieldId(Long id){
        List<MeasureValue> measureValues = measureValueRepository.listAllOnMeasureFieldId(id);
        return measureValues.stream().mapToDouble(MeasureValue::getValue).sum() / (long) measureValues.size();
    }

    @Override
    public MeasureValue findById(Long id) {
        return measureValueRepository.findById(id);
    }

    @Override
    public MeasureValue add(MeasureValue measure) {
        return measureValueRepository.add(measure);
    }

    @Override
    public MeasureValue edit(MeasureValue measure) {
        return measureValueRepository.edit(measure);
    }

    @Override
    public boolean deleteById(Long id) {
        return measureValueRepository.deleteById(id);
    }
}
