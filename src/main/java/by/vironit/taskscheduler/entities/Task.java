package by.vironit.taskscheduler.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Task(TaskGroups taskGroup,
                String title,
                String taskDescription,
                LocalDateTime startDate,
                LocalDateTime endDate,
                TaskStatus taskStatus) {

        this.taskGroup = taskGroup;
        this.title = title;
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskStatus = taskStatus;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
