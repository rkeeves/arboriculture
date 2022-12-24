package io.github.rkeeves.meta.selector;

import lombok.Value;

@Value(staticConstructor = "of")
public class SelectorCss implements Selector {

    String css;

    @Override
    public <T> T accept(SelectorVisitor<T> visitor) {
        return visitor.onSelector(this);
    }
}
