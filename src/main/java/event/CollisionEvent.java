package event;

import matter.Matter;

import java.util.EventObject;

public class CollisionEvent extends EventObject {
    Matter destination;

    public CollisionEvent(Matter source, Matter destination) {
        super(source);

        this.destination = destination;
    }

    public Matter getDestination() {
        return destination;
    }
}
