package factory;

import coordinate.Point;
import coordinate.Vector;
import designate.Feature;
import designate.Shape;
import designate.Type;
import event.CollisionEventListener;
import matter.DrawableMatter;
import matter.MovableMatter;

public class BrickFactory {

    public static final int WIDTH = 60;
    public static final int HEIGHT = 20;

    private BrickFactory() {

    }

    public static DrawableMatter createNormalBrick(Point location, Feature feature) {
        DrawableMatter brick = new DrawableMatter(location, WIDTH, HEIGHT, Type.TARGET, feature, Shape.BRICK);

        brick.addCollisionEventListener(getCollisionEventListener(brick));

        if (feature == Feature.SPEED_UP) {
            brick.addCollisionEventListener(event -> {
                if (event.getDestination().getType() != Type.DESTROYER) {
                    return;
                }

                BallFactory.speedUp();
            });
        }

        return brick;
    }

    public static MovableMatter createMovableBrick(Point location, Feature feature, Vector vector) {
        MovableMatter brick = new MovableMatter(location, WIDTH, HEIGHT, Type.TARGET, feature, Shape.BRICK, vector);

        brick.addCollisionEventListener(getCollisionEventListener(brick));
        brick.addCollisionEventListener(event -> brick.getMotion().flipX());

        return brick;
    }

    public static MovableMatter createPlayerBrick(
            Point location, int width, int height, Feature feature, Vector vector) {
        MovableMatter brick = new MovableMatter(location, width, height, Type.PLAYER, feature, Shape.BRICK, vector);

        brick.addCollisionEventListener(event -> {
            if (event.getDestination().getType() != Type.DESTROYER) {
                return;
            }

            MovableMatter ball = (MovableMatter) event.getDestination();
            Point brickLocation = brick.getLocation();
            Point ballLocation = ball.getLocation();
            Vector motion = ball.getMotion();
            motion.set(new Vector(BallFactory.nowSpeed(), calculateAngle(brickLocation, ballLocation)));

        });

        return brick;
    }

    private static CollisionEventListener getCollisionEventListener(DrawableMatter brick) {
        return event -> {
            if (event.getDestination().getType() == Type.DESTROYER) {
                brick.getDamage(1);
            }
        };
    }

    private static int calculateAngle(Point p1, Point p2) {
        int dx = p2.getX() - p1.getX();
        int dy = p2.getY() - p1.getY();
        return (int) Math.toDegrees(Math.atan2(dy, dx));
    }
}
