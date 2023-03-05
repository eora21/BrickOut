package matter;

import coordinate.Point;
import designate.Life;
import designate.Shape;
import designate.Type;
import property.Drawable;

import java.awt.*;

public class DrawableMatter extends Matter implements Drawable {
    private Life life;
    private final Shape shape;

    public DrawableMatter(Point location, int width, int height, Type type, Life life, Shape shape) {
        super(location, width, height, type);
        this.life = life;
        this.shape = shape;
    }

    public DrawableMatter(Point location, int radius, Type type, Life life, Shape shape) {
        this(location, radius, radius, type, life, shape);
    }

    public boolean isZeroStrong() {
        return life == Life.ZERO;
    }

    public void getDamage(int damage) {
        this.life = life.calculateLife(damage);
    }

    @Override
    public void draw(Graphics graphics) {
        shape.draw(graphics, getLocation(), this.getWidth(), this.getHeight(), life.getColor());
    }
}
