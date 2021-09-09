package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldSuccessfulTranserFromSecondToFirst() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);

        var cardInfo = DataHelper.getSecondCardInfo();
        dashboardPage.transferToFirstCard(cardInfo);
        System.out.println("Balance first card: " + dashboardPage.getCardBalance(0));
        System.out.println("Balance second card: " + dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldSuccessfulTranserFromFirstToSecond() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);

        var cardInfo = DataHelper.getFirstCardInfo();
        dashboardPage.transferToSecondCard(cardInfo);
        System.out.println("Balance first card: " + dashboardPage.getCardBalance(0));
        System.out.println("Balance second card: " + dashboardPage.getCardBalance(1));
    }
}
