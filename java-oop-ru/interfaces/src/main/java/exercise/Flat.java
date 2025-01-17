package exercise;

// BEGIN
public class Flat implements Home {
    private Double area;
    private Double balconyArea;
    private Integer floor;

    Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public Double getArea() {
        return this.area + this.balconyArea;
    }

    public Integer compareTo(Home otherHome) {
        return otherHome.getArea() > this.getArea() ? 1 : -1;
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + this.floor + " этаже";
    }
}

// END
