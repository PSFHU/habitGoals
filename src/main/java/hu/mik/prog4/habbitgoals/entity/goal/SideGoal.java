package hu.mik.prog4.habbitgoals.entity.goal;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
import lombok.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SideGoal extends Goal{
    private Long mainGoalId;
    private Long measureFieldId;
    private double goalValue;
}
