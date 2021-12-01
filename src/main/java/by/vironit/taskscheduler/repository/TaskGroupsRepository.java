package by.vironit.taskscheduler.repository;

import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TaskGroupsRepository extends JpaRepository<TaskGroups, Long>, JpaSpecificationExecutor<TaskGroups> {

    TaskGroups findByTitle(String title);

    TaskGroups getById(Long id);

    Page<TaskGroups> findAll(Pageable pageable);

    List<TaskGroups> getAllByAppUser(AppUser appUser);

}
