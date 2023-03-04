package designate;

import coordinate.Point;
import functional.GraphicsConsumer;
import type.Drawable;

import java.awt.*;

public enum Shape {
    BALL(Graphics::fillOval),
    BRICK(Graphics::fillRect),
    WALL(Graphics::fillRect);

    private final GraphicsConsumer<Graphics> graphicsConsumer;

    Shape(GraphicsConsumer<Graphics> graphicsConsumer) {
        this.graphicsConsumer = graphicsConsumer;
    }

    public void draw(Graphics graphics, Point point, int width, int height, Color color) {
        Color oldColor = graphics.getColor();

        graphics.setColor(color);
        graphicsConsumer.accept(graphics, point.getX(), point.getY(), width, height);

        graphics.setColor(oldColor);
    }
}

