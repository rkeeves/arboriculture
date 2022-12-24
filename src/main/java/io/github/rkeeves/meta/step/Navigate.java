package io.github.rkeeves.meta.step;

import io.github.rkeeves.meta.deed.Deed;
import lombok.Value;

@Value(staticConstructor = "toUrl")
public class Navigate implements Step {

    String url;

    @Override
    public Deed accept(StepVisitor stepVisitor) {
        return stepVisitor.onStep(this);
    }
}
