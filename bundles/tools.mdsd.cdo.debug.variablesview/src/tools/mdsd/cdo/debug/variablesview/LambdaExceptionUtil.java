package tools.mdsd.cdo.debug.variablesview;

import java.util.function.Function;
import java.util.function.IntFunction;

public class LambdaExceptionUtil {

    public static <T, R, E extends Exception> Function<T, R> wrapFn(
        FunctionWithException<T, R, E> wrappable) {
        return t -> {
            try {
                return wrappable.apply(t);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    public static <R, E extends Exception> IntFunction<R> wrapIntFn(
        IntFunctionWithException<R, E> wrappable) {
        return t -> {
            try {
                return wrappable.apply(t);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    @FunctionalInterface
    public interface FunctionWithException<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    @FunctionalInterface
    public interface IntFunctionWithException<R, E extends Exception> {
        R apply(int t) throws E;
    }

}
