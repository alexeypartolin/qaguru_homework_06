package com.alexeypartolin.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaIssuesTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1928x1080";
    }

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int NUMBER = 76;

    @Test
    void stepTest() {

        step("Открываем главную страницу", () -> open("https://github.com/"));
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий " + REPOSITORY, () -> $(By.linkText(REPOSITORY)).click());
        step("Открываем tab 'Issues'", () -> $(By.partialLinkText("Issues")).click());
        step("Поверяем наличие Issue с номером " + NUMBER, () -> {
          $(withText("#" + NUMBER)).shouldBe(Condition.visible);
        });

    }

}
