package exercise;

// BEGIN
class Circle {
    public Point point;
    public Integer radius;

    Circle(Point point, Integer radius)  {
        this.point = point;
        this.radius  = radius;
    }

    public Double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException(" Achtung !");
        }
        var square = Math.PI * this.radius * this.radius;
        return square;
    }

    public Integer getRadius() {
        return this.radius;
    }
}
// END
