package com.greenfoxacademy.connectionmysql.Controllers;

import com.greenfoxacademy.connectionmysql.Services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

   TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("todos",todoService.findAll());

        return "todolist";
    }

    @GetMapping("/delete")
    public String returnDelete(@RequestParam Long id) {
        todoService.deleteTodo(id);
        return "redirect:/list";

    }

    @GetMapping("/add")
    public String renderAdd() {
        return "add";
    }

    @PostMapping("/add")
    public String returnAdd(@RequestParam String title) {
        todoService.save(title);
        return "redirect:/list";
    }

    @GetMapping("/{id}/edit")
    public String renderEdit(Model model, @PathVariable Long id) {
        model.addAttribute("todos",todoService.findById(id).get());
        model.addAttribute("assignees",todoService.findAllAssignees());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String returnEdit(@PathVariable Long id, @RequestParam String title, @RequestParam (name = "done", defaultValue = "false") Boolean done, @RequestParam (name = "urgent", defaultValue = "false") Boolean urgent, @RequestParam String assignee) {
        todoService.updateTodo(id,title,done,urgent);
        todoService.updateTodoAssignee(id, assignee);
        return "redirect:/list";
    }
    @PostMapping("/search")
    public String returnSearch(Model model, @RequestParam String search) {
        model.addAttribute("searches",todoService.findAllByTitleContaining(search));
        return "search";
    }

    @GetMapping("/assignees")
    public String renderAssignees(Model model) {
        model.addAttribute("assignees",todoService.findAllAssignees());

        return "assignees";
    }

    @PostMapping("/assignees")
    public String returnAssignees(Model model, @RequestParam String name, @RequestParam String email) {
        model.addAttribute("assignees",todoService.findAllAssignees());
        todoService.addAssignee(name, email);

        return "redirect:/assignees";
    }

    @GetMapping("/deleteAssignee")
    public String returnDeleteAssignee(@RequestParam Long id) {
        todoService.deleteAssigneeById(id);
        return "redirect:/assignees";

    }

    @GetMapping("/{id}/assigneeEdit")
    public String renderAssigneeEdit(Model model, @PathVariable Long id) {
        model.addAttribute("assignees",todoService.findAssigneeById(id));
        return "assigneeEdit";
    }

    @PostMapping("/{id}/assigneeEdit")
    public String returnAssigneeEdit(@PathVariable Long id, @RequestParam String name, String email) {
        todoService.updateAssignee(id, name, email);
        return "redirect:/assignees";
    }

    @GetMapping("/{id}/todosAssignee")
    public String returnTodosAssignee(Model model, @PathVariable Long id) {
        model.addAttribute("assignees",todoService.findAssigneeById(id).getName());
        model.addAttribute("todosList",todoService.findTodosByAssigneeId(id));


        return "todosAssignee";
    }
}
