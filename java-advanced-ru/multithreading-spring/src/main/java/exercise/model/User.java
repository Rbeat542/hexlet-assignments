package exercise.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table; // это другой table, не тот, что в обычном запросе  Spring

@Getter
@Setter
@Table("users")
public class User {

    // Идентификатор будет генерироваться автоматически
    @Id
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
