package io.github.rkeeves.meta.deed.result;

import lombok.Value;

@Value(staticConstructor = "of")
public class Good implements Result {

    String message;

    @Override
    public boolean isGood() {
        return true;
    }
}
