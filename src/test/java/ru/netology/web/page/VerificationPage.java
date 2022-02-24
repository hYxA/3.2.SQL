package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private static final SelenideElement verifyCodeField = $("[data-test-id=code] input");
    private static final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private static final SelenideElement errorTransfer = $("[data-test-id='error-notification']");
    private static final SelenideElement errorBlocked = $("[data-test-id='error-notification'] div.notification__content");

    public VerificationPage() {
        verifyCodeField.shouldBe(Condition.visible);
    }

    public DashBoardPage validVerify(String verificationCode) {
        verifyCodeField.setValue(verificationCode);
        verifyButton.click();

        return new DashBoardPage();
    }

    public static void invalidVerifyCode(String verificationCode) {

        verifyCodeField.setValue(verificationCode);
        verifyButton.click();

        errorTransfer.shouldBe(Condition.visible);
    }

    public void manyClickInvalidVerify(String verificationCode) {
        verifyCodeField.setValue(verificationCode);
        verifyButton.click();
        verifyButton.click();
        verifyButton.click();
        verifyButton.click();
    }
}
