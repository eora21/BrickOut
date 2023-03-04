package coordinate;

public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor.
     *
     * @param other original data
     */
    public Point(Point other) {
        this(other.getX(), other.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * 주어진 값만큼 이동 시킴.
     *
     * @param dx X축 이동량
     * @param dy Y축 이동량
     * @return
     */
    public Point move(int dx, int dy) {
        x += dx;
        y += dy;

        return this;
    }

    /**
     * 주어진 변위량 만큼 이동
     *
     * @param displacement 변위량
     * @return
     */
    public Point move(Point displacement) {
        x += displacement.getX();
        y += displacement.getY();

        return this;
    }

    /**
     * 주어진 좌표로 이동
     *
     * @param other 이동할 위치
     * @return
     */
    public Point moveTo(Point other) {
        x = other.getX();
        y = other.getY();

        return this;
    }

    /**
     * 주어진 좌표로 이동
     *
     * @param x 이동할 위치의 x 좌표
     * @param y 이동할 위치의 y 좌표
     * @return
     */
    public Point moveTo(int x, int y) {
        this.x = x;
        this.y = y;

        return this;
    }
}

