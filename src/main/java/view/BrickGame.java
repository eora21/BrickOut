package view;

import coordinate.Point;
import coordinate.Vector;
import designate.Life;
import factory.BallFactory;
import factory.BrickFactory;

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
        world.add(BallFactory.create(
                new Point(200, 300), 20, new Vector(10, -90), world.getMatters()));
        world.add(BallFactory.create(
                new Point(300, 300), 20, new Vector(10, -45), world.getMatters()));
        world.add(BrickFactory.create(new Point(200, 100), 60, 20, Life.ONE));
        world.run();
    }
}
