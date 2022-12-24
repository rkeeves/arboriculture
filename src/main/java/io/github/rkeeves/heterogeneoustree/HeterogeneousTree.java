package io.github.rkeeves.heterogeneoustree;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface HeterogeneousTree<N, L> {

    <T> T match(BiFunction<N, List<HeterogeneousTree<N, L>>, T> mapNode, Function<L, T> mapLeaf);

    static <N, L, T> T bottomUp(HeterogeneousTree<N, L> tree, BiFunction<N, List<T>, T> mapNode, Function<L, T> mapLeaf) {
        return tree.match((n, subTrees) ->
                mapNode.apply(n, subTrees.stream()
                        .map(subTree -> bottomUp(subTree, mapNode, mapLeaf))
                        .collect(Collectors.toList())),
                mapLeaf);
    }

    static <N, L> HeterogeneousTree<N, L> many(N data, List<HeterogeneousTree<N, L>> subTrees) {
        return new Many<>(data, subTrees);
    }

    static <N, L> HeterogeneousTree<N, L> one(L data) {
        return new One<>(data);
    }
}
