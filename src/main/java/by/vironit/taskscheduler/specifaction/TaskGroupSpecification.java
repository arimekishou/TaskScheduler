package by.vironit.taskscheduler.specifaction;

import by.vironit.taskscheduler.entities.TaskGroups;
import org.springframework.data.jpa.domain.Specification;

public class TaskGroupSpecification {

    public static Specification<TaskGroups> idTaskGroups(Long minId, Long maxId) {
        if (minId == null) minId = Long.valueOf(1);
        if (maxId == null) maxId = Long.MAX_VALUE;
        Long finalMinId = minId;
        Long finalMaxId = maxId;
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("Id"), finalMinId, finalMaxId));
    }

    public static Specification<TaskGroups> titleTaskGroups(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<String>get("title"), title);
    }
}

