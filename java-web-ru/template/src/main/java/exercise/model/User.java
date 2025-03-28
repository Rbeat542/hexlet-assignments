package exercise.model;

import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
public final class User {

    private long id;
    private String firstName;

    @ToString.Include
    private String lastName;
    private String email;

    public User(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
