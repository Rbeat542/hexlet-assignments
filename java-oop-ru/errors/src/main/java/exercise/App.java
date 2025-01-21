package exercise;

// BEGIN
class App {
    public static void app(String[] args) {
    }

    public static void printSquare(Circle circle) {
        try {
            Double area = circle.getSquare();
            System.out.println(Math.round(area));
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        }
        System.out.println("Вычисление окончено");
    }
}
// END
