package com.greenfoxacademy.connectionmysql.Services;

import com.greenfoxacademy.connectionmysql.Models.Assignee;
import com.greenfoxacademy.connectionmysql.Models.Todo;
import com.greenfoxacademy.connectionmysql.Repositories.AssigneeRepository;
import com.greenfoxacademy.connectionmysql.Repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    TodoRepository todoRepository;

    AssigneeRepository assigneeRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, AssigneeRepository asigneeRepository) {
        this.todoRepository = todoRepository;
        this.assigneeRepository = asigneeRepository;
    }

    public List<Todo> onlyFiltered(Boolean isDone) {
        return todoRepository.findAll().stream().filter(todo -> todo.getDone() == isDone).collect(Collectors.toList());
    }

    public void save(String title) {
        Todo todo = new Todo(title,false,false);
        todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findAll().stream().filter(todo -> todo.getId() == id).findAny();
    }

    public List<Todo> findAllByTitleContaining(String search) {
        return todoRepository.findAllByTitleContaining(search);
    }

    public List<Assignee> findAllAssignees() {
        return assigneeRepository.findAll();
    }

    public void addAssignee(String name, String email) {
        Assignee assignee = new Assignee(name,email);
        assigneeRepository.save(assignee);
    }


    //@Transactional
    public void deleteAssigneeById(Long id) {
        assigneeRepository.deleteById(id);
    }

    public void updateTodo(Long id, String title, Boolean done, Boolean urgent)  {

        if(title == "") {
            String title2 = todoRepository.findById(id).get().getTitle();
            Todo todo = new Todo(id,title2,done,urgent);

            todoRepository.save(todo);
        } else {
            Todo todo = new Todo(id,title,done,urgent);

            todoRepository.save(todo);
        }
    }

    public Assignee findAssigneeById(Long id) {
        return assigneeRepository.findAssigneeById(id).get();
    }

    public void updateAssignee(Long id, String name, String email) {
        if(name == "" && email == "") {
            String name2 = assigneeRepository.findAssigneeById(id).get().getName();
            String email2 = assigneeRepository.findAssigneeById(id).get().getEmail();

            Assignee assignee = new Assignee(id,name2,email2);

            assigneeRepository.save(assignee);

        } else if (name == "") {
            String name2 = assigneeRepository.findAssigneeById(id).get().getName();

            Assignee assignee = new Assignee(id,name2,email);

            assigneeRepository.save(assignee);
        } else if (email == "") {
            String email2 = assigneeRepository.findAssigneeById(id).get().getEmail();

            Assignee assignee = new Assignee(id,name,email2);

            assigneeRepository.save(assignee);
        } else {
            Assignee assignee = new Assignee(id,name,email);

            assigneeRepository.save(assignee);
        }
    }

    public void updateTodoAssignee(Long id, String name) {
        String title = todoRepository.findById(id).get().getTitle();
        Boolean done = todoRepository.findById(id).get().getDone();
        Boolean urgent = todoRepository.findById(id).get().getUrgent();
        Assignee assignee = assigneeRepository.findAssigneeByName(name).get();

        Todo todo = new Todo(id,title,urgent,done,assignee);

        todoRepository.save(todo);
    }

    public List<Todo> findTodosByAssigneeId(Long id) {

        return todoRepository.findAllByAssigneeId(id);
    }
}
