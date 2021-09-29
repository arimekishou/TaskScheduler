package app.entities;


import java.sql.Date;
import java.util.Objects;

public class Task {
    private int taskGroupId;
    private int id;
    private String title;
    private String taskDescription;
    private Date startDate;
    private Date endDate;
    private String status;

    public int getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(int taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskGroupId=" + taskGroupId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskGroupId == task.taskGroupId && id == task.id && Objects.equals(title, task.title) &&
                Objects.equals(taskDescription, task.taskDescription) && Objects.equals(startDate, task.startDate) &&
                Objects.equals(endDate, task.endDate) && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskGroupId, id, title, taskDescription, startDate, endDate, status);
    }
}
