package io.github.rkeeves.meta.step;

import io.github.rkeeves.meta.deed.Deed;

public interface StepVisitor {

    Deed onStep(Group group);

    Deed onStep(Navigate navigate);

    Deed onStep(Click click);

    Deed onStep(Type type);
}
