package designate;

import java.awt.*;

public enum Life {
    ZERO(0, Color.BLACK),
    ONE(1, Color.GREEN),
    TWO(2, Color.YELLOW),
    THREE(3, Color.ORANGE),
    FOUR(4, Color.RED),
    INFINITY(99, Color.DARK_GRAY);

    private final int value;
    private final Color color;

    Life(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public static Life calculateLife(Life beforeLife, int damage) {
        if (damage < 1) {
            return beforeLife;
        }

        if (beforeLife == INFINITY) {
            return INFINITY;
        }

        int nowLifeValue = Math.max(beforeLife.value - damage, 0);

        return values()[nowLifeValue];
    }

    public Color getColor() {
        return color;
    }
}
