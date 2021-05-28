package hu.mik.prog4.habbitgoals.entity.measure;

import lombok.*;

import java.sql.Date;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MeasureValue extends Measure{
    private Long measureFieldId;
    private double value;
    private Date date;
}
