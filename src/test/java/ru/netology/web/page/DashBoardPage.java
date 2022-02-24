package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashBoardPage {
    private SelenideElement head = $("[data-test-id='dashboard']");

    public void isDashboardPage() {
        head.shouldBe(visible);
    }
}

