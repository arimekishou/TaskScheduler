package by.vironit.taskscheduler.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
public class TaskGroups {

    @SequenceGenerator(
            name = "task_groups_sequence",
            sequenceName = "task_groups_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_groups_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public TaskGroups(AppUser appUser, String title) {
        this.appUser = appUser;
        this.title = title;

    }

}
