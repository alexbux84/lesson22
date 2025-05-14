package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideElement financeFreedom = $x("//span[text()='Финансовая свобода']/parent::span[@id='accounts-can-spend']");
    private ElementsCollection cards = $$x("//*[contains(@class,'account-cards')]//*[@data-content]");

    public SelenideElement getFinanceFreedom() {
        return financeFreedom;
    }

    public ElementsCollection getCards() {
        return cards;
    }
}
