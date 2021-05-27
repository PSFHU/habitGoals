package hu.mik.prog4.habbitgoals.entity.goal;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MainGoal extends Goal{
    private List<SideGoal> sideGoals;
}
