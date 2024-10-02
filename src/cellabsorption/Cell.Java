package cellabsorption;

public class Cell{

    private static final double
        WIGGLINESS = 0.2,
        WANDER_FROM_CENTER = 60000;
    private Ellipse shape;
    private double radius;
    private double direction;
    private double centerX;
    private double centerY;


    public Cell(double centerX, double centerY, double radius){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.shape = new Ellipse(centerX, centerY, radius * 2, radius * 2);
        shape.setFillColor(color);
        this.direction = normalizeRadians(Math.random() * Math.PI * 2);
    }

    // private void createCell(double x, double y, double radius, Color color) {
    //     this.shape = new Ellipse(x, y, radius * 2, radius * 2);
    //     shape.setFillColor(color);
    //     this.radius = radius;
    //     direction = normalizeRadians(Math.random() * Math.PI * 2);
    // }

    private void setRadius(double newRadius) {
        if (newRadius < 0) {
            newRadius = 0;
        }
        radius = newRadius;
        Point previousCenter = shape.getCenter();
        shape.setSize(new Point(newRadius * 2, newRadius * 2));
        shape.setCenter(previousCenter);
    }

    private void grow(double amount) {
        setRadius(radius + amount);
    }

    private static double sqr(double x) {
        return x * x;
    }

    private static double normalizeRadians(double theta) {
        double pi2 = Math.PI * 2;
        return ((theta + Math.PI) % pi2 + pi2) % pi2 - Math.PI;
    }

    private void moveAround(Point centerOfGravity) {
        shape.moveBy(Math.cos(direction), Math.sin(direction));

        double distToCenter = shape.getCenter().distance(centerOfGravity);
        double angleToCenter = centerOfGravity.subtract(shape.getCenter()).angle();
        double turnTowardCenter = normalizeRadians(angleToCenter - direction);

        direction = normalizeRadians(
            direction
                + (Math.random() - 0.5) * WIGGLINESS
                + turnTowardCenter * Math.tanh(distToCenter / WANDER_FROM_CENTER));
    }

}