package io.github.rkeeves.meta.selector;

public interface Selector {

    <T> T accept(SelectorVisitor<T> visitor);
}
