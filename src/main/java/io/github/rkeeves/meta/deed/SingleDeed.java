package io.github.rkeeves.meta.deed;

import io.github.rkeeves.meta.deed.result.Result;
import lombok.Value;

@Value(staticConstructor = "of")
public class SingleDeed implements Deed {

    Result result;

    @Override
    public void accept(DeedVisitor deedVisitor) {
        deedVisitor.onDeed(this);
    }
}
