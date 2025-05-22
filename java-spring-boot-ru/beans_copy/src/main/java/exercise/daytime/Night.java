package exercise.daytime;
import jakarta.annotation.PostConstruct;

import java.time.LocalTime;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void message() {
        var hours = LocalTime.now().getHour();
        System.out.println("Time now is " + hours + " hours");
        System.out.println("Time now is a Night");
    }
    // END
}
