package matter;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import designate.Shape;
import designate.Type;

import java.awt.*;
import java.util.List;

public class BouncyMatter extends MovableMatter {
    private final List<Matter> worldMatters;

    public BouncyMatter(Point location, int width, int height, Type type, Life life, Shape shape, Vector motion,
                           List<Matter> worldMatters) {
        super(location, width, height, type, life, shape, motion);
        this.worldMatters = worldMatters;
    }

    public BouncyMatter(Point location, int radius, Type type, Life life, Shape shape, Vector motion,
                           List<Matter> worldMatters) {
        this(location, radius, radius, type, life, shape, motion, worldMatters);
    }

    @Override
    public void next() {
        super.next();

        synchronized (worldMatters) {
            for (Matter matter : worldMatters) {
                if (isCollision(matter) && this.getType() != matter.getType()) {
                    this.hit(matter);
                    matter.hit(this);
                }
            }
        }
    }
}
