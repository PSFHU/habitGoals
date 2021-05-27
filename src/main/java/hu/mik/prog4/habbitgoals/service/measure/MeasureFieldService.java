package hu.mik.prog4.habbitgoals.service.measure;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
import hu.mik.prog4.habbitgoals.repository.measure.MeasureFieldRepository;

import java.util.List;

public class MeasureFieldService implements MeasureService<MeasureField> {

    private final MeasureFieldRepository measureFieldRepository;

    public MeasureFieldService() {
        measureFieldRepository = new MeasureFieldRepository();
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
    public MeasureField add(MeasureField measure) {
        return measureFieldRepository.add(measure);
    }

    @Override
    public MeasureField edit(MeasureField measure) {
        return measureFieldRepository.edit(measure);
    }

    @Override
    public boolean deleteById(Long id) {
        return measureFieldRepository.deleteById(id);
    }
}
