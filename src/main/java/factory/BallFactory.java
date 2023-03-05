package factory;

import coordinate.Point;
import coordinate.Vector;
import designate.Feature;
import designate.Shape;
import designate.Type;
import matter.Matter;
import matter.MovableMatter;

import java.awt.*;
import java.util.List;

public class BallFactory {
    private static final int BALL_SPEED = 10;
    private static int addSpeed = 0;

    private BallFactory() {

    }

    public static MovableMatter create(Point location, int radius, int angle, List<Matter> matters) {
        MovableMatter ball = new MovableMatter(
                location, radius, Type.DESTROYER, Feature.UNBREAKABLE, Shape.BALL,
                new Vector(BALL_SPEED + addSpeed, angle));

        ball.addCollisionEventListener(event -> {
            Matter matter = event.getDestination();
            Rectangle intersection = ball.getIntersection(matter);
            if ((ball.getWidth() == intersection.getWidth())
                    || (matter.getWidth() == intersection.getWidth())) {
                ball.getMotion().flipY();
            } else if ((ball.getHeight() == intersection.getHeight())
                    || (matter.getHeight() == intersection.getHeight())) {
                ball.getMotion().flipX();
            } else {
                ball.getMotion().flipY();
                ball.getMotion().flipX();
            }
        });

        return ball;
    }

    public static int nowSpeed() {
        return BALL_SPEED + addSpeed;
    }

    public static void speedUp() {
        addSpeed++;
    }

    public static void resetSpeed() {
        addSpeed = 0;
    }
}
