package io.github.rkeeves.story;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.rkeeves.playwright.PlaywrightVisitor;
import io.github.rkeeves.print.PrintVisitor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TellTheStoryUsingPlaywrightTest {

    @Test
    void test() {
        final var deed = IntentionallyFailingStory.story().accept(PlaywrightVisitor.of(page));
        deed.accept(PrintVisitor.of(System.out));
        assertFalse(deed.getResult().isGood(), "This story must not end well");
    }

    static Playwright playwright;

    static Browser browser;

    BrowserContext context;

    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        context.setDefaultTimeout(8000L);
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
