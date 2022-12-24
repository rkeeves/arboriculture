package io.github.rkeeves.meta.deed;

import io.github.rkeeves.meta.deed.result.Result;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class GroupDeed implements Deed {

    Result result;

    List<Deed> subDeeds;

    @Override
    public void accept(DeedVisitor deedVisitor) {
        deedVisitor.onDeed(this);
    }
}
