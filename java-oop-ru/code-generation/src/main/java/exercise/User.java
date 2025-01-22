package exercise;

// BEGIN
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// END

class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
