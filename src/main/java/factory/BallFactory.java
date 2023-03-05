package factory;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import designate.Shape;
import designate.Type;
import matter.BouncyMatter;
import matter.Matter;

import java.awt.*;
import java.util.List;

public class BallFactory {

    private BallFactory() {

    }

    public static BouncyMatter create(Point location, int radius, Vector vector, List<Matter> matters) {
        BouncyMatter ball = new BouncyMatter(
                location, radius, Type.DESTROYER, Life.INFINITY, Shape.BALL, vector, matters);

        ball.addCollisionEventListener(event -> {
            Matter matter = event.getDestination();
            Rectangle intersection = ball.getIntersection(matter);
            if ((ball.getWidth() == intersection.getWidth())
                    || (matter.getWidth() == intersection.getWidth())) {
                ball.getMotion().flipY();
            }
            if ((ball.getHeight() == intersection.getHeight())
                    || (matter.getHeight() == intersection.getHeight())) {
                ball.getMotion().flipX();
            }
        });

        return ball;
    }
}
