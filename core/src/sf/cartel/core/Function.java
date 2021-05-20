package sf.cartel.core;

@FunctionalInterface
public interface Function<T, R> {
    R call(T t);
}