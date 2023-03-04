package matter;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import designate.Shape;
import event.CollisionEvent;
import event.CollisionEventListener;
import type.Bouncible;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.List;

public class BouncyFigure extends MovableFigure implements Bouncible {
    private final List<Matter> worldMatters;
    EventListenerList listenerList;

    public BouncyFigure(Point location, int width, int height, Shape shape, Life life, Vector motion,
                        List<Matter> worldMatters) {
        super(location, width, height, shape, life, motion);
        this.worldMatters = worldMatters;
        listenerList = new EventListenerList();
    }

    public BouncyFigure(Point location, int radius, Shape shape, Life life, Vector motion,
                        List<Matter> worldMatters) {
        this(location, radius, radius, shape, life, motion, worldMatters);
    }

    @Override
    public void next() {
        super.next();

        synchronized (worldMatters) {
            for (Matter matter : worldMatters) {
                if (isCollision(matter) && this.getShape() != matter.getShape()) {
                    Rectangle intersection = getIntersection(matter);
                    if ((getWidth() == intersection.getWidth())
                            || (matter.getWidth() == intersection.getWidth())) {
                        getMotion().flipY();
                    } else if ((getHeight() == intersection.getHeight())
                            || (matter.getHeight() == intersection.getHeight())) {
                        getMotion().flipX();
                    } else {
                        getMotion().flipY();
                        getMotion().flipX();
                    }

                    CollisionEventListener[] listeners = listenerList.getListeners(CollisionEventListener.class);
                    for (CollisionEventListener listener : listeners) {
                        listener.collisionEvent(new CollisionEvent(this, matter));
                    }
                }
            }
        }
    }

    @Override
    public void addCollisionEventListener(CollisionEventListener listener) {
        listenerList.add(CollisionEventListener.class, listener);
    }
}
