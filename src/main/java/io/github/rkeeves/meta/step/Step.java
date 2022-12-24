package io.github.rkeeves.meta.step;

import io.github.rkeeves.meta.deed.Deed;

public interface Step {

    Deed accept(StepVisitor stepVisitor);
}
