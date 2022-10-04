package com.greenfoxacademy.connectionmysql.Repositories;

import com.greenfoxacademy.connectionmysql.Models.Assignee;
import com.greenfoxacademy.connectionmysql.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Long> {

Optional<Assignee> findAssigneeById(Long id);

Optional<Assignee> findAssigneeByName(String name);

}
