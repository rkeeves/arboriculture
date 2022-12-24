package io.github.rkeeves.story;

import io.github.rkeeves.meta.selector.Selector;
import io.github.rkeeves.meta.selector.SelectorCss;
import io.github.rkeeves.meta.step.Click;
import io.github.rkeeves.meta.step.Group;
import io.github.rkeeves.meta.step.Navigate;
import io.github.rkeeves.meta.step.Step;
import io.github.rkeeves.meta.step.Type;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IntentionallyFailingStory {

    final Selector USERNAME = SelectorCss.of("*[data-test='username']");

    final Selector PASSWORD = SelectorCss.of("*[data-test='password']");

    final Selector LOGIN_BUTTON = SelectorCss.of("*[data-test='login-button']");

    final Selector MISSING_LINK_TO_CAT_PICTURES = SelectorCss.of("*[data-test='missing-link']");

    final Selector OTHER_MISSING_LINK_TO_CAT_PICTURES = SelectorCss.of("*[data-test='other-missing-link']");

    public static Step story() {
        return Group.of(
                "If I login, I can look at cat pictures!",
                Group.of(
                        "Let's login!",
                        Navigate.toUrl("https://www.saucedemo.com/"),
                        Group.of("Let's fill the username and password fields!",
                                Type.into(USERNAME, "standard_user"),
                                Type.into(PASSWORD, "secret_sauce")),
                        Click.on(LOGIN_BUTTON)
                ),
                Group.of(
                        "Now show me those cat pictures!",
                        Click.on(MISSING_LINK_TO_CAT_PICTURES),
                        Click.on(OTHER_MISSING_LINK_TO_CAT_PICTURES)
                )
        );
    }
}
