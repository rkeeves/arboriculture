package io.github.rkeeves.heterogeneoustree;

public interface EitherFooOrBar {

    <T> T convert(EitherFooOrBarMapper<T> eitherFooOrBarMapper);
}
