package exercise;

import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;


@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Autowired
    private PersonRepository personRepository;

    @Container
    private static PostgreSQLContainer<?> dataB = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("dbname")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");


    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", dataB::getJdbcUrl);
        registry.add("spring.datasource.username", dataB::getUsername);
        registry.add("spring.datasource.password", dataB::getPassword);
    }


    @Test
    @Transactional
    void testShowAllPersons() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(
                        get("/people")
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("Jassica");
        assertThat(response.getContentAsString()).contains("Smith");
        assertThat(personRepository.findAll()).isNotEmpty();

    }

    @Test
    @Transactional
    void testShowPerson() throws Exception {
        MockHttpServletResponse responseGet = mockMvc
                .perform(
                        get("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(responseGet.getStatus()).isEqualTo(200);
        assertThat(responseGet.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(responseGet.getContentAsString()).contains("John", "Smith");
        assertThat(personRepository.findById(1).getFirstName()).isEqualTo("John");

    }

    @Test
    @Transactional
    void testDeletePerson() throws Exception {
        MockHttpServletResponse responseDelete = mockMvc
                .perform(
                        delete("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(responseDelete.getStatus()).isEqualTo(200);
        assertThat(personRepository.findById(1)).isEqualTo(null);

    }

    @Test
    @Transactional
    void testPatch() throws Exception {
        MockHttpServletResponse responsePatch = mockMvc
                .perform(
                    patch("/people/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Vasya\", \"lastName\": \"Pupkin\"}")
                )
                .andReturn()
                .getResponse();

        assertThat(personRepository.findById(1).getFirstName()).isEqualTo("Vasya");
        assertThat(responsePatch.getStatus()).isEqualTo(200);

    }
    // END


    @Test
    @Transactional
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
