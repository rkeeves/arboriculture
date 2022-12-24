package io.github.rkeeves.heterogeneoustree;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class One<N, L> implements HeterogeneousTree<N, L> {

    L data;

    @Override
    public <T> T match(BiFunction<N, List<HeterogeneousTree<N, L>>, T> mapNode, Function<L, T> mapLeaf) {
        return mapLeaf.apply(data);
    }
}
