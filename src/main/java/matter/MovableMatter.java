package matter;

import coordinate.Point;
import coordinate.Vector;
import designate.Feature;
import designate.Shape;
import designate.Type;
import property.Movable;
import view.BrickWorld;

import java.util.ArrayList;
import java.util.List;

public class MovableMatter extends DrawableMatter implements Movable {
    private final Thread thread;
    private final Vector motion;
    private final List<Vector> effects;

    public MovableMatter(Point location, int width, int height, Type type, Feature feature, Shape shape, Vector motion) {
        super(location, width, height, type, feature, shape);
        this.motion = motion;
        thread = new Thread(this);
        effects = new ArrayList<>();
    }

    public MovableMatter(Point location, int radius, Type type, Feature feature, Shape shape, Vector motion) {
        this(location, radius, radius, type, feature, shape, motion);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                next();
                Thread.sleep(BrickWorld.INTERVAL);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public boolean isZeroStrong() {
        if (super.isZeroStrong()) {
            stop();
            return true;
        }

        return false;
    }

    @Override
    public void setMotion(Vector motion) {
        this.motion.set(motion);
    }

    @Override
    public Vector getMotion() {
        return motion;
    }

    @Override
    public void addEffect(Vector effect) {
        effects.add(effect);
    }

    @Override
    public void next() {
        for (Vector effect : effects) {
            getMotion().add(effect);
        }

        getLocation().move(motion.getDisplacement());

        synchronized (getMatters()) {
            for (Matter matter : getMatters()) {
                if (isCollision(matter) && this.getType() != matter.getType()) {
                    this.hit(matter);

                    if (!(matter instanceof Movable)) {
                        matter.hit(this);
                    }
                }
            }
        }
    }
}
