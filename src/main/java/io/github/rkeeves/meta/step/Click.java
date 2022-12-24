package io.github.rkeeves.meta.step;

import io.github.rkeeves.meta.deed.Deed;
import io.github.rkeeves.meta.selector.Selector;
import lombok.Value;

@Value(staticConstructor = "on")
public class Click implements Step {

    Selector selector;

    @Override
    public Deed accept(StepVisitor stepVisitor) {
        return stepVisitor.onStep(this);
    }
}
