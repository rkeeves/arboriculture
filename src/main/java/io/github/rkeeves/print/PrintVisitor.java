package io.github.rkeeves.print;

import io.github.rkeeves.meta.deed.DeedVisitor;
import io.github.rkeeves.meta.deed.GroupDeed;
import io.github.rkeeves.meta.deed.SingleDeed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.PrintStream;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PrintVisitor implements DeedVisitor {

    PrintStream printStream;

    private int depth;

    public static PrintVisitor of(PrintStream printStream) {
        return new PrintVisitor(printStream, 0);
    }

    @Override
    public void onDeed(GroupDeed groupDeed) {
        printStream.println("  ".repeat(depth) + groupDeed.getResult());
        depth++;
        for (var subDeed : groupDeed.getSubDeeds()) {
            subDeed.accept(this);
        }
        depth--;
    }

    @Override
    public void onDeed(SingleDeed singleDeed) {
        printStream.println("  ".repeat(depth) + singleDeed.getResult());
    }
}
