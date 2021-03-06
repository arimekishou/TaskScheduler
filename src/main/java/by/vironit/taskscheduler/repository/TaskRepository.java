package by.vironit.taskscheduler.repository;

import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.enums.TaskStatus;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    Task findByTitle(String title);

    Task findByTaskStatus(TaskStatus taskStatus);

    void deleteTaskByTaskGroupId(Long id);

    Task getById(Long id);

    @NonNull
    Page<Task> findAll(@NonNull Pageable pageable);

    List<Task> getAllByTaskGroup(TaskGroups taskGroups);

    List<Task> getAllByTaskStatus(TaskStatus taskStatus);

}
