package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@AllArgsConstructor
@Getter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN

    public String serialize() throws Exception {
        Car car = new Car(getId(), getBrand(), getModel(), getColor(), getOwner());
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(car);  //через writeAsBytes toString не сработало
        return str;
    }


    public static Car deSerialize(String jsonString) throws Exception {
        //Car car = new Car();
        ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to reuse!
        Car newCar  = mapper.readValue(jsonString, Car.class);
        return newCar;
    }
    // END
}
