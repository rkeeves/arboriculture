package io.github.rkeeves.selenium;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.function.Function;

@Value
public class SeleniumVisitor implements StepVisitor {

    WebDriverWait webDriverWait;

    SeleniumSelectorVisitor seleniumSelectorVisitor;

    public static SeleniumVisitor of(WebDriverWait webDriverWait) {
        return new SeleniumVisitor(webDriverWait, new SeleniumSelectorVisitor());
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
            webDriverWait.until(Function.identity()).navigate().to(navigate.getUrl());
            return SingleDeed.of(Result.good(navigate.toString()));
        } catch (Throwable t) {
            return SingleDeed.of(Result.bad(navigate.toString(), t));
        }
    }

    @Override
    public Deed onStep(Click click) {
        final var locator = click.getSelector().accept(seleniumSelectorVisitor);
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            return SingleDeed.of(Result.good(click.toString()));
        } catch (Throwable t) {
            return SingleDeed.of(Result.bad(click.toString(), t));
        }

    }

    @Override
    public Deed onStep(Type type) {
        try {
            final var locator = type.getSelector().accept(seleniumSelectorVisitor);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(type.getText());
            return SingleDeed.of(Result.good(type.toString()));
        } catch (Throwable t) {
            return SingleDeed.of(Result.bad(type.toString(), t));
        }
    }
}