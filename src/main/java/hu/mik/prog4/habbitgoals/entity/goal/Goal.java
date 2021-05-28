package hu.mik.prog4.habbitgoals.entity.goal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Goal {
    private Long id;
    private String title;
    private Boolean isCompleted;
}
