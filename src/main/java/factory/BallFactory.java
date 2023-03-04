package factory;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import designate.Shape;
import event.CollisionEventListener;
import matter.BouncyFigure;
import matter.Matter;
import matter.MovableFigure;

import java.util.List;

public class BallFactory {

    private BallFactory() {

    }
    public static BouncyFigure create(Point location, int radius, Vector vector, List<Matter> worldMatters) {
        BouncyFigure ball = new BouncyFigure(location, radius, radius, Shape.BALL, Life.INFINITY, vector, worldMatters);

        ball.addCollisionEventListener(event -> {
            System.out.println("col");
            Matter matter = event.getDestination();
            if (matter.getShape() == Shape.BRICK) {
                System.out.println("brick");
            }
        });

//        ball.addCollisionEventListener(event -> {
//            if (event.getDestination().getShape() == Shape.BALL) {
//                System.out.println("ball");
//            }
//        });
//
//        ball.addCollisionEventListener(event -> {
//            if (event.getDestination().getShape() == Shape.WALL) {
//                System.out.println("wall");
//            }
//        });

        return ball;
    }
}
