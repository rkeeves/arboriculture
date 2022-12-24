package io.github.rkeeves.selenium;

import io.github.rkeeves.meta.selector.SelectorCss;
import io.github.rkeeves.meta.selector.SelectorVisitor;
import lombok.Value;
import org.openqa.selenium.By;

@Value
public class SeleniumSelectorVisitor implements SelectorVisitor<By> {

    @Override
    public By onSelector(SelectorCss selectorCss) {
        return By.cssSelector(selectorCss.getCss());
    }
}
