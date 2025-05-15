package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (var method : Address.class.getMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                String methodName = method.getName();
                String type = method.getReturnType().toString();
                System.out.println("Method " + methodName + " returns a value of type " + type + ".");
            }
        }
    }
}        // END

