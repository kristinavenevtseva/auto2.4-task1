package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private ElementsCollection transferButtons = $$("[data-test-id=action-deposit]");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement actionButton = $("[data-test-id=action-transfer]");

    public void transferToFirstCard(DataHelper.CardInfo cardInfo) {
        transferButtons.first().click();
        amountField.shouldBe(visible);
        amountField.clear();
        amountField.setValue(DataHelper.getRandomAmount());
        fromField.clear();
        fromField.setValue(cardInfo.getNumber());
        actionButton.click();
        heading.shouldBe(visible);
    }

    public void transferToSecondCard(DataHelper.CardInfo cardInfo) {
        transferButtons.last().click();
        amountField.shouldBe(visible);
        amountField.clear();
        amountField.setValue(DataHelper.getRandomAmount());
        fromField.clear();
        fromField.setValue(cardInfo.getNumber());
        actionButton.click();
        heading.shouldBe(visible);
    }

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getCardBalance(int index) {
        var text = cards.get(index).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}