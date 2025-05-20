package exercise.controller;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "")
    public List<Task> index() {
        return taskRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Task show(@PathVariable long id) {

        var task =  taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        return task;
    }

    // BEGIN
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public Task create(@RequestBody Task task) {
        taskRepository.save(task);
        return task;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}")
    public Task add(@PathVariable Long id, @RequestBody Task task) {
        var taskOptional =  taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            var taskFound = taskOptional.get();
            taskFound.setTitle(task.getTitle());
            taskFound.setDescription(task.getDescription());
        } else {
            taskRepository.save(task);
            System.out.println("PUT command failed. Task with id = " + id + " notfound, thus it was saved");
        }
        return task;
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
}
