package by.vironit.taskscheduler.repository;

import by.vironit.taskscheduler.entities.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    Optional<Task> findById(Long aLong);

    @Override
    <S extends Task> List<S> findAll(Example<S> example);

    @Override
    <S extends Task> S saveAndFlush(S entity);

    @Override
    void delete(Task entity);

}
