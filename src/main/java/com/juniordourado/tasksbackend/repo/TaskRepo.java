package com.juniordourado.tasksbackend.repo;

import com.juniordourado.tasksbackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
