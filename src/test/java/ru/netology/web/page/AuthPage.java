package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthPage {
    private String authUrl = "http://127.0.0.1:9999";
    private SelenideElement login =   $("[data-test-id=\"login\"] input");
    private SelenideElement password = $("[data-test-id=\"password\"] input");
    private SelenideElement buttonLogin = $("[data-test-id=\"action-login\"]");
    private SelenideElement errorTransfer = $("[data-test-id='error-notification']");
    private SelenideElement errorBlocked = $("[data-test-id='error-notification' div.notification__content]");

    public AuthPage() {
        open(authUrl);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo){
        login.setValue(authInfo.getLogin());
        password.setValue(authInfo.getPassword());
        buttonLogin.click();

        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo authInfo) {
        login.setValue(authInfo.getLogin());
        password.setValue(authInfo.getPassword());
        buttonLogin.click();

        errorTransfer.shouldBe(Condition.visible);
    }

    public void manyClickInvalidPassword(DataHelper.AuthInfo authInfo) {
        login.setValue(authInfo.getLogin());
        password.setValue(authInfo.getPassword());
        buttonLogin.click();
        buttonLogin.click();
        buttonLogin.click();
    }

    public void validLoginWithBlockStatus(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        buttonLogin.click();
    }

    public SelenideElement getErrorBlocked() {
        return errorBlocked;
    }
}
