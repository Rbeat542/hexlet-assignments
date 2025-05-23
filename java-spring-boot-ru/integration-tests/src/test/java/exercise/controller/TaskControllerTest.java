package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@AutoConfigureMockMvc
@SpringBootTest
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = getNewTask();
        taskRepository.save(task);

        var id = taskRepository.findAll().getLast().getId();
        var result = mockMvc.perform(get("/tasks/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body.contains(task.getTitle()));
    }

    @Test
    public void testCreate() throws Exception {
        var task = getNewTask();
        //taskRepository.save(task);


        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        //assertThat(taskRepository.findAll().size()).isEqualTo(1);
        assertThat(taskRepository.findAll().getLast().getTitle()).isEqualTo(task.getTitle());

    }

    @Test
    public void testUpdate() throws Exception {

        var task = getNewTask();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("description", "Some description");
        data.put("title", "new title");

        var request = put("/tasks/" + task.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(data));

        var resp = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = resp.getResponse().getContentAsString();
        //assertThatJson(body).node("title").isString().isEqualTo("new title");
        //assertThatJson(body).node("description").isString().isEqualTo("Some description");
        assertThatJson(body).and(
                a -> a.node("title").isString().isEqualTo("new title"),
                a -> a.node("description").isString().isEqualTo("Some description")
        );
    }

    @Test
    public void testDelete() throws Exception {
        Long id;
        if (!taskRepository.findAll().isEmpty()) {
            id = taskRepository.findAll().getLast().getId();

            var result = mockMvc.perform(delete("/tasks/{id}", id))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(taskRepository.findById(id).isEmpty());
        }
    }

    public Task getNewTask() {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().sentence(2))
                .ignore(Select.field(Task::getDescription))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .create();
        return task;
    }
// END

}
