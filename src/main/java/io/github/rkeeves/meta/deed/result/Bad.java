package io.github.rkeeves.meta.deed.result;

import lombok.Value;

@Value(staticConstructor = "of")
public class Bad implements Result {

    String message;

    @Override
    public boolean isGood() {
        return false;
    }
}
