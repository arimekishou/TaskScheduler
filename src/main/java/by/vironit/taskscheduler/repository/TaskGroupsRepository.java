package by.vironit.taskscheduler.repository;

import by.vironit.taskscheduler.entities.TaskGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TaskGroupsRepository extends JpaRepository<TaskGroups, Long> {

}
