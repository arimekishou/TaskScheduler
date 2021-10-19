package by.vironit.taskscheduler.entities;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "task_groups")
@Entity
public class TaskGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title")
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskGroups)) return false;
        TaskGroups that = (TaskGroups) o;
        return getId().equals(that.getId()) && getUser().equals(that.getUser()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getTitle());
    }

    @Override
    public String toString() {
        return "TaskGroups{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                '}';
    }

}
