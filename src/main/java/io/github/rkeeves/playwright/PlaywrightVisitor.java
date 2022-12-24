package io.github.rkeeves.playwright;

import com.microsoft.playwright.Page;
import io.github.rkeeves.meta.deed.Deed;
import io.github.rkeeves.meta.deed.GroupDeed;
import io.github.rkeeves.meta.deed.SingleDeed;
import io.github.rkeeves.meta.deed.result.Result;
import io.github.rkeeves.meta.step.Click;
import io.github.rkeeves.meta.step.Group;
import io.github.rkeeves.meta.step.Navigate;
import io.github.rkeeves.meta.step.StepVisitor;
import io.github.rkeeves.meta.step.Type;
import lombok.Value;

import java.util.ArrayList;

@Value
public class PlaywrightVisitor implements StepVisitor {

    Page page;

    PlaywrightSelectorVisitor playwrightSelectorVisitor;

    public static PlaywrightVisitor of(Page page) {
        return new PlaywrightVisitor(page, new PlaywrightSelectorVisitor(page));
    }

    @Override
    public Deed onStep(Group group) {
        final var subDeeds = new ArrayList<Deed>();
        for (var subStep : group.getSubSteps()) {
            final var deed = subStep.accept(this);
            subDeeds.add(deed);
            if (!deed.getResult().isGood()) {
                return GroupDeed.of(Result.bad(group.getLabel()), subDeeds);
            }
        }
        return GroupDeed.of(Result.good(group.getLabel()), subDeeds);
    }

    @Override
    public Deed onStep(Navigate navigate) {
        try {
            page.navigate(navigate.getUrl());
            return SingleDeed.of(Result.good(navigate.toString()));
        } catch (Throwable t) {
            return SingleDeed.of(Result.bad(navigate.toString(), t));
        }
    }

    @Override
    public Deed onStep(Click click) {
        try {
            click.getSelector().accept(playwrightSelectorVisitor).click();
            return SingleDeed.of(Result.good(click.toString()));
        } catch (Throwable t) {
            return SingleDeed.of(Result.bad(click.toString(), t));
        }
    }

    @Override
    public Deed onStep(Type type) {
        try {
            type.getSelector().accept(playwrightSelectorVisitor).type(type.getText());
            return SingleDeed.of(Result.good(type.toString()));
        } catch (Throwable t) {
            return SingleDeed.of(Result.bad(type.toString(), t));
        }
    }
}