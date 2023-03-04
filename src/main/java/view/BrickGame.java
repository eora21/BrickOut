package view;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import designate.Shape;
import factory.BallFactory;
import factory.BrickFactory;
import matter.BouncyFigure;
import matter.Figure;
import matter.MovableFigure;

import javax.swing.*;

public class BrickGame extends JFrame {
    private final BrickWorld world;

    public BrickGame(int width, int height) {
        setSize(width, height);
        setLayout(null);

        world = new BrickWorld(getWidth() - 100, getHeight() - 200);
        world.setLocation(50, 100);
        add(world);
    }

    public void run() {
        world.add(new Figure(new Point(100, 100), 100, Shape.BALL, Life.INFINITY));
        world.add(new MovableFigure(
                new Point(100, 300), 20, Shape.BALL, Life.INFINITY, new Vector(10, -90)));
        world.add(BallFactory.create(
                new Point(200, 300), 20, new Vector(10, -90), world.getMatters()));
        world.add(new BouncyFigure(
                new Point(200, 300),
                20, Shape.BALL, Life.INFINITY, new Vector(10, 0), world.getMatters()));
        world.add(BallFactory.create(
                new Point(300, 300), 20, new Vector(10, -45), world.getMatters()));
        world.add(BrickFactory.create(new Point(200, 100), 60, 10, Life.ONE));
        world.run();
    }
}
