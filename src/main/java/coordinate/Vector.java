package coordinate;

public class Vector {
    int magnitude;
    int angle;
    Point displacement;

    public Vector() {
        this(0, 0);
    }

    public Vector(int magnitude, int angle) {
        this.magnitude = magnitude;
        this.angle = angle;
        this.displacement = new Point(0, 0);
        updateDisplacementVector();
    }

    public Vector(Point displacement) {
        this.displacement = displacement;

        updateDirectionVector();
    }

    public int getMagnitude() {
        return magnitude;
    }

    public int getAngle() {
        return angle;
    }

    public Point getDisplacement() {
        return displacement;
    }

    public Vector set(Vector other) {
        magnitude = other.getMagnitude();
        angle = other.getAngle();
        displacement.moveTo(other.displacement);

        return this;
    }

    public Vector add(Vector other) {
        displacement.move(other.getDisplacement());

        updateDirectionVector();

        return this;
    }

    public Vector multiply(int scale) {
        magnitude *= scale;

        updateDisplacementVector();

        return this;
    }

    public Vector rotate(int angle) {
        this.angle += angle;

        updateDisplacementVector();

        return this;
    }

    public Vector flipX() {
        displacement.moveTo(-displacement.getX(), displacement.getY());

        updateDirectionVector();

        return this;
    }

    public Vector flipY() {
        displacement.moveTo(displacement.getX(), -displacement.getY());

        updateDirectionVector();

        return this;
    }

    protected void updateDisplacementVector() {
        displacement.moveTo(
                (int) (magnitude * Math.cos(Math.toRadians(angle))),
                (int) (magnitude * Math.sin(Math.toRadians(angle)))
        );
    }

    protected void updateDirectionVector() {
        magnitude = (int) Math.sqrt(Math.pow(displacement.getX(), 2) + Math.pow(displacement.getY(), 2));
        if (magnitude != 0) {
            angle = (int) Math.toDegrees(Math.acos((double) displacement.getX() / magnitude));
            if (displacement.getY() < 0) {
                angle = -angle;
            }
        } else {
            angle = 0;
        }
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
        updateDisplacementVector();
    }

    public void addAngle(int angle) {
        this.angle += angle;
        updateDisplacementVector();
    }
}

