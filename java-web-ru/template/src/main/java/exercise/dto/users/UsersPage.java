// Путь exercise/dto/users/UsersPage.java
package exercise.dto.users;

import exercise.model.User;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN

// END
@AllArgsConstructor
@Getter
public class UsersPage {
    private List<User> users;
    private String header;

    }