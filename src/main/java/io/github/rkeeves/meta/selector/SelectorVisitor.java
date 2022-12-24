package io.github.rkeeves.meta.selector;

public interface SelectorVisitor<T> {

    T onSelector(SelectorCss selectorCss);
}
