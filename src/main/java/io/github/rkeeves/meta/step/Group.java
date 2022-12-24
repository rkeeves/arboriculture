package io.github.rkeeves.meta.step;

import io.github.rkeeves.meta.deed.Deed;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

@Value(staticConstructor = "of")
public class Group implements Step {

    String label;

    List<Step> subSteps;

    public static Step of(String label, Step... subSteps) {
        return Group.of(label, Arrays.asList(subSteps));
    }

    @Override
    public Deed accept(StepVisitor stepVisitor) {
        return stepVisitor.onStep(this);
    }
}
