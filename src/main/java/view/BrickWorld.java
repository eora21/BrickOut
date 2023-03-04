package view;

import coordinate.Point;
import designate.Shape;
import matter.Figure;
import matter.Matter;
import type.Movable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BrickWorld extends JPanel {
    public static final long INTERVAL = 20L;
    private final List<Matter> matters;

    public BrickWorld(int width, int height) {
        setSize(width, height);
        matters = new ArrayList<>();
        add(new Matter(new Point(-getWidth() / 2, -getHeight() / 2),
                getWidth() * 3, getHeight(), Shape.WALL));
        add(new Matter(new Point(-getWidth() / 2, getHeight() / 2),
                getWidth(), getHeight() * 3, Shape.WALL));
        add(new Matter(new Point(getWidth() / 2 * 3, getHeight() / 2),
                getWidth(), getHeight() * 3, Shape.WALL));
    }

    public void add(Matter matter) {
        synchronized (matters) {
            matters.add(matter);

            if (matter instanceof Movable) {
                ((Movable) matter).start();
            }
        }

    }

    public List<Matter> getMatters() {
        return matters;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                repaint();
                Thread.sleep(INTERVAL);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawRect(0, 0, getWidth(), getHeight());
        synchronized (matters) {
            matters.stream()
                    .filter(Figure.class::isInstance)
                    .map(Figure.class::cast)
                    .collect(Collectors.toList())
                    .forEach(figure -> figure.draw(graphics));
        }
    }
}
