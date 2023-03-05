package designate;

import java.awt.*;

public enum Feature {
    DEAD(0, Color.BLACK),
    NORMAL(1, Color.GREEN),
    SPEED_UP(1, Color.ORANGE),
    TWO_LIFE(2, Color.YELLOW),
    UNBREAKABLE(99, Color.DARK_GRAY);

    private final int value;
    private final Color color;

    Feature(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public Feature calculateLife(int damage) {
        if (damage < 1) {
            return this;
        }

        if (this == UNBREAKABLE) {
            return this;
        }

        int nowLifeValue = Math.max(this.value - damage, 0);

        return values()[nowLifeValue];
    }

    public Color getColor() {
        return color;
    }
}
