package factory;

import coordinate.Point;
import coordinate.Vector;
import designate.Feature;
import designate.Shape;
import designate.Type;
import event.CollisionEventListener;
import matter.DrawableMatter;
import matter.MovableMatter;
import view.BrickGame;

public class BrickFactory {

    public static final int WIDTH = 60;
    public static final int HEIGHT = 20;

    private BrickFactory() {

    }

    public static DrawableMatter createNormalBrick(Point location, Feature feature) {
        DrawableMatter brick = new DrawableMatter(location, WIDTH, HEIGHT, Type.TARGET, feature, Shape.BRICK);
        defaultBrickSetting(brick);
        return brick;
    }

    public static MovableMatter createMovableBrick(Point location, Feature feature, Vector vector) {
        MovableMatter brick = new MovableMatter(location, WIDTH, HEIGHT, Type.TARGET, feature, Shape.BRICK, vector);
        defaultBrickSetting(brick);
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

    private static void defaultBrickSetting(DrawableMatter brick) {
        brick.addCollisionEventListener(getCollisionEventListener(brick));
        Feature feature = brick.getFeature();

        if (feature == Feature.SPEED_UP) {
            brick.addCollisionEventListener(event -> {
                if (event.getDestination().getType() != Type.DESTROYER) {
                    return;
                }

                BallFactory.speedUp();
            });
        }

        if (feature != Feature.UNBREAKABLE && feature != Feature.DEAD) {
            BrickGame.plusLeftBlock();
        }
    }

    private static CollisionEventListener getCollisionEventListener(DrawableMatter brick) {
        return event -> {
            if (event.getDestination().getType() == Type.DESTROYER) {
                BrickGame.plusScore(10);
                brick.getDamage(1);
            }

            if (brick.isZeroStrong()) {
                BrickGame.plusScore(20);
                BrickGame.minusLeftBlock();
            }
        };
    }

    private static int calculateAngle(Point p1, Point p2) {
        int dx = p2.getX() - p1.getX();
        int dy = p2.getY() - p1.getY();
        return (int) Math.toDegrees(Math.atan2(dy, dx));
    }
}
