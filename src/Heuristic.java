@FunctionalInterface
public interface Heuristic<N, V, R> {
    R apply(N n, V v);
}
