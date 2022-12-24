package io.github.rkeeves.heterogeneoustree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.rkeeves.heterogeneoustree.HeterogeneousTree.many;
import static io.github.rkeeves.heterogeneoustree.HeterogeneousTree.one;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeterogeneousTreeTest {

    @Test
    @DisplayName("We can traverse like a bottom-up parser, and also handle the attachment(either Foo or Bar type) of leafs.")
    void test() {
        final List<Integer> list = HeterogeneousTree.bottomUp(HETEROGENOUS_TREE,
                (x, ys) -> Stream.concat(
                            ys.stream()
                                    .flatMap(Collection::stream),
                            Stream.of(Integer.parseInt(x))
                    ).collect(Collectors.toList()),
                x -> List.of(x.convert(FOO_OR_BAR_TO_INT)));

        assertEquals(List.of(3, 6, 4, 1, 5, 2, 0), list);
    }

    static final HeterogeneousTree<String, EitherFooOrBar> HETEROGENOUS_TREE = many(
            "0",
            List.of(
                    many("1",
                            List.of(
                                    one(Foo.of(3)),
                                    many("4",
                                            List.of(
                                                    one(Bar.of("6"))
                                            ))
                            )),
                    many("2",
                            List.of(
                                    one(Bar.of("5"))))
            )
    );

    static final EitherFooOrBarMapper<Integer> FOO_OR_BAR_TO_INT = new EitherFooOrBarMapper<>() {

        @Override
        public Integer onTyped(Foo foo) {
            return foo.getX();
        }

        @Override
        public Integer onTyped(Bar bar) {
            return Integer.parseInt(bar.getX());
        }
    };
}
