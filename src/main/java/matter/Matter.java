package matter;

import coordinate.Point;
import designate.Shape;

import java.awt.*;

public class Matter {
    private final Point location;
    private int width;
    private int height;
    private final Shape shape;

    public Matter(Point location, int width, int height, Shape shape) {
        this.location = location;
        this.width = width;
        this.height = height;
        this.shape = shape;
    }

    public Point getLocation() {
        return location;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinX() {
        return location.getX() - width / 2;
    }

    public int getMinY() {
        return location.getY() - height / 2;
    }

    public int getMaxX() {
        return location.getX() + width / 2;
    }

    public int getMaxY() {
        return location.getY() + height / 2;
    }

    public boolean isCollision(Matter other) {
        return getRectangle().intersects(other.getRectangle());
    }

    protected Rectangle getIntersection(Matter other) {
        return getRectangle().intersection(other.getRectangle());
    }

    protected Rectangle getRectangle() {
        return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }

    public Shape getShape() {
        return shape;
    }
}
