package exercise;

// BEGIN
class NegativeRadiusException extends Exception {
    //public message = "Не удалось посчитать площадь";

    NegativeRadiusException(String message) {
        super(message);
    }
}
// END
