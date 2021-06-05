package sf.cartel.core;

@FunctionalInterface
public interface Consumer2<T, G> {
    void call(T t, G g);
}

