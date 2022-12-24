package io.github.rkeeves.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.github.rkeeves.meta.selector.SelectorCss;
import io.github.rkeeves.meta.selector.SelectorVisitor;
import lombok.Value;

@Value
public class PlaywrightSelectorVisitor implements SelectorVisitor<Locator> {

    Page page;

    @Override
    public Locator onSelector(SelectorCss selectorCss) {
        return page.locator("css=" + selectorCss.getCss());
    }
}
