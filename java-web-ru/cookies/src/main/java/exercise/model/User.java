package exercise.model;

import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
public final class User {

    private Long id;
    private String firstName;

    @ToString.Include
    private String lastName;

    private String email;
    private String encriptedPassword;
    private String token;

    public User(String firstName, String lastName, String email, String encriptedPassword, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.encriptedPassword = encriptedPassword;
        this.token = token;
    }

    public Long getId() {
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

    public String getEncriptedPassword() {
        return encriptedPassword;
    }

    public String getToken() {
        return token;
    }
}
