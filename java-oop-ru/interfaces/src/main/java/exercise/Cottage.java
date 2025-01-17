package exercise;

// BEGIN
public class Cottage implements Home {
    private Double area;
    private Integer floorCount;

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public Double getArea() {
        return this.area;
    }

    public Integer compareTo(Home otherHome) {
        return (otherHome.getArea() > this.getArea()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }
}
// END
