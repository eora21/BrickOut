package matter;

import coordinate.Point;
import designate.Feature;
import designate.Shape;
import designate.Type;
import property.Drawable;

import java.awt.*;

public class DrawableMatter extends Matter implements Drawable {
    private Feature feature;
    private final Shape shape;

    public DrawableMatter(Point location, int width, int height, Type type, Feature feature, Shape shape) {
        super(location, width, height, type);
        this.feature = feature;
        this.shape = shape;
    }

    public DrawableMatter(Point location, int radius, Type type, Feature feature, Shape shape) {
        this(location, radius, radius, type, feature, shape);
    }

    public void kill() {
        this.feature = Feature.DEAD;
    }

    public boolean isZeroStrong() {
        return feature == Feature.DEAD;
    }

    public void getDamage(int damage) {
        this.feature = feature.calculateLife(damage);
    }

    @Override
    public void draw(Graphics graphics) {
        shape.draw(graphics, this, feature.getColor());
    }
}
