package io.github.rkeeves.heterogeneoustree;

import lombok.Value;

@Value(staticConstructor = "of")
public class Bar implements EitherFooOrBar {

    String x;

    @Override
    public <T> T convert(EitherFooOrBarMapper<T> eitherFooOrBarMapper) {
        return eitherFooOrBarMapper.onTyped(this);
    }
}
