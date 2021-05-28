package hu.mik.prog4.habbitgoals.service.measure;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import hu.mik.prog4.habbitgoals.repository.measure.MeasureValueAbstractRepository;
import hu.mik.prog4.habbitgoals.service.Service;

import java.util.List;

public class MeasureValueService implements Service<MeasureValue> {

    private final MeasureValueAbstractRepository measureValueRepository;

    public MeasureValueService() {
        measureValueRepository = new MeasureValueAbstractRepository();
    }

    @Override
    public List<MeasureValue> listAll() {
        return measureValueRepository.listAll();
    }

    @Override
    public MeasureValue findById(Long id) {
        return measureValueRepository.findById(id);
    }

    @Override
    public MeasureValue create(MeasureValue measure) {
        return measureValueRepository.create(measure);
    }

    @Override
    public MeasureValue update(MeasureValue measure) {
        return measureValueRepository.update(measure);
    }

    @Override
    public boolean deleteById(Long id) {
        return measureValueRepository.deleteById(id);
    }
}
