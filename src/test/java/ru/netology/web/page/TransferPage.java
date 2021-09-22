package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement newHeading = $(byText("Пополнение карты"));

    public TransferPage() {
        newHeading.shouldBe(visible);
    }

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement actionButton = $("[data-test-id=action-transfer]");

    public DashboardPage transferToCard(DataHelper.CardInfo cardInfo,int amount) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(cardInfo.getNumber());
        actionButton.click();
        return new DashboardPage();
    }

    public int getAmount() {
        var text = $(".money-input__value").text().replaceAll("\\s", "");
        return Integer.parseInt(text);
    }
}
