package factory;

import coordinate.Point;
import designate.Life;
import designate.Shape;
import designate.Type;
import matter.DrawableMatter;

public class BrickFactory {
    private BrickFactory() {

    }
    public static DrawableMatter create(Point location, int width, int height, Life life) {
        DrawableMatter brick = new DrawableMatter(location, width, height, Type.TARGET, life, Shape.BRICK);

        brick.addCollisionEventListener(event -> {
            if (event.getDestination().getType() == Type.DESTROYER) {
                brick.getDamage(1);
            }
        });

        return brick;
    }
}
