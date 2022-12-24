package io.github.rkeeves.meta.deed.result;

public interface Result {

    boolean isGood();

    static Result good(String message) {
        return Good.of(message);
    }

    static Result bad(String message) {
        return Bad.of(message);
    }

    static Result bad(String message, Throwable t) {
        return BadWithThrowable.of(message, t);
    }
}
