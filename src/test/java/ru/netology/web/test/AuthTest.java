package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.AuthPage;
import ru.netology.web.page.DashBoardPage;

public class AuthTest {

    @AfterAll
    static void cleanAllTables() {
        DataHelper.cleanData();
    }

    @Test
    void shouldDashBoardPage() {
        val authPage = new AuthPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = authPage.validLogin(authInfo);
        val codeVerify = DataHelper.getVerificationCode();
        verificationPage.validVerify(String.valueOf(codeVerify));
        val dashBoardPage = new DashBoardPage();
        dashBoardPage.itIsDashboardPage();
    }

    @Test
    void shouldGetErrorByInvalidLogin() {
        val authPage = new AuthPage();
        val authInfo = DataHelper.getBadAuthInfo();

        authPage.invalidLogin(authInfo);
    }

    @Test
    void shouldGetErrorByInvalidVerifyCode() {
        val authPage = new AuthPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationCode = authPage.validLogin(authInfo);
        val verifyBadCode = DataHelper.getBadVerificationCode();

        verificationCode.invalidVerifyCode(verifyBadCode);

    }


}
