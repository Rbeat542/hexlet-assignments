package exercise;

import lombok.SneakyThrows;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN

class App {

    public static void save(Path path, Car car) throws Exception {
        String str = car.serialize();
        Files.write(path, str.getBytes());
    }

    @SneakyThrows
    public static Car extract(Path path) throws Exception {
        String str = Files.readString(path).toString();
        Car newCar  = Car.deSerialize(str);
        return newCar;
    }
}
// END
