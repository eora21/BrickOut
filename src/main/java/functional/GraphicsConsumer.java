package functional;

import java.awt.*;

@FunctionalInterface
public interface GraphicsConsumer {
    void accept(Graphics graphics, int x, int y, int width, int height);
}
