package designate;

import coordinate.Point;
import functional.GraphicsConsumer;

import java.awt.*;

public enum Shape {
    BALL(Graphics::fillOval),
    BRICK(Graphics::fillRect);

    private final GraphicsConsumer graphicsConsumer;

    Shape(GraphicsConsumer graphicsConsumer) {
        this.graphicsConsumer = graphicsConsumer;
    }

    public void draw(Graphics graphics, Point point, int width, int height, Color color) {
        Color oldColor = graphics.getColor();

        graphics.setColor(color);
        graphicsConsumer.accept(graphics, point.getX(), point.getY(), width, height);

        graphics.setColor(oldColor);
    }
}
