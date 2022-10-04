package com.greenfoxacademy.connectionmysql;

import com.greenfoxacademy.connectionmysql.Controllers.TodoController;
import com.greenfoxacademy.connectionmysql.Models.Todo;
import com.greenfoxacademy.connectionmysql.Repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectionMySqlApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConnectionMySqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*Todo todo1 = new Todo("clean the bathroom and the kitchen",true, true);
        Todo todo2 = new Todo("wipe all the surfaces with a cloth",false, true);
        Todo todo3 = new Todo("remove the grease",true, true);

        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);*/

    }
}
