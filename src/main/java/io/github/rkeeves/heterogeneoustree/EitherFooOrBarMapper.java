package io.github.rkeeves.heterogeneoustree;

public interface EitherFooOrBarMapper<T> {

    T onTyped(Foo foo);

    T onTyped(Bar bar);
}
