package view;

import coordinate.Point;
import designate.Type;
import matter.DrawableMatter;
import matter.Matter;
import property.Movable;

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
                getWidth() * 3, getHeight(), Type.BARRICADE));
        add(new Matter(new Point(-getWidth() / 2, getHeight() / 2),
                getWidth(), getHeight() * 3, Type.BARRICADE));
        add(new Matter(new Point(getWidth() / 2 * 3, getHeight() / 2),
                getWidth(), getHeight() * 3, Type.BARRICADE));
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
                synchronized (matters) {
                    matters.removeIf(matter ->
                            matter instanceof DrawableMatter && ((DrawableMatter)matter).isZeroStrong());
                }
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
                    .filter(DrawableMatter.class::isInstance)
                    .map(DrawableMatter.class::cast)
                    .collect(Collectors.toList())
                    .forEach(drawableMatter -> drawableMatter.draw(graphics));
        }
    }
}
