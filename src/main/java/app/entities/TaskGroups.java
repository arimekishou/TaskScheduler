package app.entities;

import java.util.Objects;

public class TaskGroups {
    private int id;
    private int userId;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Task_Groups{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskGroups that = (TaskGroups) o;
        return id == that.id && userId == that.userId && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title);
    }
}
