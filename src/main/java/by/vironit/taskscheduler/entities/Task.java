package by.vironit.taskscheduler.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_group_id", nullable = false)
    private TaskGroups taskGroup;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "task_description", length = 1000)
    private String taskDescription;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status", length = 15)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskGroups getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroups taskGroup) {
        this.taskGroup = taskGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getId().equals(task.getId()) && getTaskGroup().equals(task.getTaskGroup()) && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getTaskDescription(), task.getTaskDescription()) && Objects.equals(getStartDate(), task.getStartDate()) && Objects.equals(getEndDate(), task.getEndDate()) && Objects.equals(getStatus(), task.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTaskGroup(), getTitle(), getTaskDescription(), getStartDate(), getEndDate(), getStatus());
    }

}