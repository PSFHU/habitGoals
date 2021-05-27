package hu.mik.prog4.habbitgoals.service.measure;

import hu.mik.prog4.habbitgoals.entity.measure.Measure;

import java.util.List;

public interface MeasureService<T extends Measure>{
    List<T> listAll();

    T findById(Long id);

    T add(T measure);

    T edit(T measure);

    boolean deleteById(Long id);
}
