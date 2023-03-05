package designate;

import functional.GraphicsConsumer;
import matter.DrawableMatter;

import java.awt.*;

public enum Shape {
    BALL(Graphics::drawOval, Graphics::fillOval),
    BRICK(Graphics::drawRect, Graphics::fillRect);

    private final GraphicsConsumer drawConsumer;
    private final GraphicsConsumer fillConsumer;

    Shape(GraphicsConsumer drawConsumer, GraphicsConsumer fillConsumer) {
        this.drawConsumer = drawConsumer;
        this.fillConsumer = fillConsumer;
    }

    public void draw(Graphics graphics, DrawableMatter matter, Color color) {
        Color oldColor = graphics.getColor();

        graphics.setColor(Color.BLACK);
        drawConsumer.accept(graphics, matter.getMinX(), matter.getMinY(), matter.getWidth(), matter.getHeight());
        graphics.setColor(color);
        fillConsumer.accept(graphics, matter.getMinX(), matter.getMinY(), matter.getWidth(), matter.getHeight());

        graphics.setColor(oldColor);
    }
}
