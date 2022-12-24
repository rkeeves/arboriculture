package io.github.rkeeves.meta.deed;

import io.github.rkeeves.meta.deed.result.Result;

public interface Deed {

    Result getResult();

    void accept(DeedVisitor deedVisitor);
}
