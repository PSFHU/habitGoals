package hu.mik.prog4.habbitgoals.service.measure;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
import hu.mik.prog4.habbitgoals.repository.measure.MeasureFieldAbstractRepository;
import hu.mik.prog4.habbitgoals.service.Service;

import java.util.List;

public class MeasureFieldService implements Service<MeasureField> {

    private final MeasureFieldAbstractRepository measureFieldRepository;

    public MeasureFieldService() {
        measureFieldRepository = new MeasureFieldAbstractRepository();
    }

    @Override
    public List<MeasureField> listAll() {
        return measureFieldRepository.listAll();
    }

    @Override
    public MeasureField findById(Long id) {
        return measureFieldRepository.findById(id);
    }

    @Override
    public MeasureField create(MeasureField measure) {
        return measureFieldRepository.create(measure);
    }

    @Override
    public MeasureField update(MeasureField measure) {
        return measureFieldRepository.update(measure);
    }

    @Override
    public boolean deleteById(Long id) {
        return measureFieldRepository.deleteById(id);
    }
}
