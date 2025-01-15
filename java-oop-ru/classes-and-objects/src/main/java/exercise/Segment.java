package exercise;
//import excercise.Point;
// BEGIN
public class Segment {
    private Point startPoint;
    private Point endPoint;

    public Segment(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return this.startPoint;
    }

    public Point getEndPoint() {
        return this.endPoint;
    }

    public Point getMidPoint() {
        Integer halfX = (this.getBeginPoint().getX() - this.getEndPoint().getX()) / 2;
        Integer halfY = (this.getBeginPoint().getY() - this.getEndPoint().getY()) / 2;
        Integer midX = this.getBeginPoint().getX() - halfX;
        Integer midY = this.getBeginPoint().getY() - halfY;
        Point midPoint = new Point(midX, midY);
        return midPoint;
    }
}
// END
