package io.github.rkeeves.meta.deed.result;

import lombok.Value;

@Value(staticConstructor = "of")
public class BadWithThrowable implements Result {

    String message;

    Throwable throwable;

    @Override
    public boolean isGood() {
        return false;
    }
}
