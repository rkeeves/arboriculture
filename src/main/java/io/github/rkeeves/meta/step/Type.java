package io.github.rkeeves.meta.step;

import io.github.rkeeves.meta.deed.Deed;
import io.github.rkeeves.meta.selector.Selector;
import lombok.Value;

@Value(staticConstructor = "into")
public class Type implements Step {

    Selector selector;

    String text;

    @Override
    public Deed accept(StepVisitor stepVisitor) {
        return stepVisitor.onStep(this);
    }
}