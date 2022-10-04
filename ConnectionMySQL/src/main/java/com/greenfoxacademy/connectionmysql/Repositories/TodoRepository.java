package com.greenfoxacademy.connectionmysql.Repositories;

import com.greenfoxacademy.connectionmysql.Models.Assignee;
import com.greenfoxacademy.connectionmysql.Models.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByTitleContaining(@Param("search") String search);

    Optional<Todo> findById(Long id);

    Optional<Todo> findByTitle(Long id);

    List<Todo> findAllByAssigneeId(Long id);

}

