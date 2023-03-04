package matter;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import designate.Shape;
import type.Movable;
import view.BrickWorld;

import java.util.ArrayList;
import java.util.List;

public class MovableFigure extends Figure implements Movable {
    private final Thread thread;
    private final Vector motion;
    private final List<Vector> effects;

    public MovableFigure(Point location, int width, int height, Shape shape, Life life, Vector motion) {
        super(location, width, height, shape, life);
        this.motion = motion;
        thread = new Thread(this);
        effects = new ArrayList<>();
    }

    public MovableFigure(Point location, int radius, Shape shape, Life life, Vector motion) {
        this(location, radius, radius, shape, life, motion);
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
    }
}
