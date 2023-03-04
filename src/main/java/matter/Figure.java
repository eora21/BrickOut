package matter;

import coordinate.Point;
import designate.Shape;
import designate.Life;
import type.Drawable;

import java.awt.*;

public class Figure extends Matter implements Drawable {
    private Life life;

    public Figure(Point location, int width, int height, Shape shape, Life life) {
        super(location, width, height, shape);
        this.life = life;
    }

    public Figure(Point location, int radius, Shape shape, Life life) {
        this(location, radius, radius, shape, life);
    }

    public boolean isZeroStrong() {
        return life == Life.ZERO;
    }

    @Override
    public void draw(Graphics graphics) {
        getShape().draw(graphics, this.getLocation(), this.getWidth(), this.getHeight(), life.getColor());
    }
}
