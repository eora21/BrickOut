package functional;

@FunctionalInterface
public interface GraphicsConsumer<T> {
    void accept(T t, int x, int y, int width, int height);
}
