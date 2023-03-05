package matter;

import coordinate.Point;
import designate.Type;
import event.CollisionEvent;
import event.CollisionEventListener;

import javax.swing.event.EventListenerList;
import java.awt.*;

public class Matter {
    private final Point location;
    private int width;
    private int height;
    private final Type type;
    private final EventListenerList listenerList;

    public Matter(Point location, int width, int height, Type type) {
        this.location = location;
        this.width = width;
        this.height = height;
        this.type = type;
        this.listenerList = new EventListenerList();
    }

    public void hit(Matter matter) {
        CollisionEventListener[] listeners = getListenerList().getListeners(CollisionEventListener.class);
        for (CollisionEventListener listener : listeners) {
            listener.collisionEvent(new CollisionEvent(this, matter));
        }
    }

    public void addCollisionEventListener(CollisionEventListener listener) {
        listenerList.add(CollisionEventListener.class, listener);
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

    public Type getType() {
        return type;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public boolean isCollision(Matter other) {
        return getRectangle().intersects(other.getRectangle());
    }

    public Rectangle getIntersection(Matter other) {
        return getRectangle().intersection(other.getRectangle());
    }

    protected Rectangle getRectangle() {
        return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }
}
