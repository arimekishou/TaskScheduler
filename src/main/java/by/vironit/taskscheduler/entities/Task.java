package by.vironit.taskscheduler.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
public class Task {

    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_groups_id", nullable = false)
    private TaskGroups taskGroup;

    @Column
    private String title;

    @Column
    private String taskDescription;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private String status;

    public Task(TaskGroups taskGroup,
                String title,
                String taskDescription,
                LocalDateTime startDate,
                LocalDateTime endDate,
                String status) {

        this.taskGroup = taskGroup;
        this.title = title;
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;

    }

}
