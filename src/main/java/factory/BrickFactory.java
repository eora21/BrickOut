package factory;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import designate.Shape;
import matter.BouncyFigure;
import matter.Figure;
import matter.Matter;

import java.util.List;

public class BrickFactory {
    private BrickFactory() {

    }
    public static Figure create(Point location, int width, int height, Life life) {
        Figure brick = new Figure(location, width, height, Shape.BRICK, life);

//        brick.addCollisionEventListener(event -> {
//            if (event.getDestination().getShape() == Shape.BRICK) {
//                System.out.println("brick");
//            }
//        });
//
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
//
//        return ball;
        return brick;
    }
}
