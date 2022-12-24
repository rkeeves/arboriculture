package io.github.rkeeves.story;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.rkeeves.print.PrintVisitor;
import io.github.rkeeves.selenium.SeleniumVisitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.util.function.Function.identity;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TellTheStoryUsingSeleniumTest {

    @Test
    void test() {
        final var deed = IntentionallyFailingStory.story().accept(SeleniumVisitor.of(await));
        deed.accept(PrintVisitor.of(System.out));
        assertFalse(deed.getResult().isGood(), "This story must not end well");
    }

    WebDriverWait await;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach() {
        await = new WebDriverWait(new ChromeDriver(), Duration.ofSeconds(8L));
    }

    @AfterEach
    void afterEach() {
        if (await != null) await.until(identity()).quit();
    }
}
