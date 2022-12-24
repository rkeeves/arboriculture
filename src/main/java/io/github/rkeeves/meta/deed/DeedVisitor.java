package io.github.rkeeves.meta.deed;

public interface DeedVisitor {

    void onDeed(GroupDeed groupDeed);

    void onDeed(SingleDeed singleDeed);
}
